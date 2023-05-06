import com.appiron.appleservices.common.api.ApacheHttpTransport;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.resource.ResourceUtil;
import com.appiron.appleservices.common.JSON;
import com.appiron.appleservices.vpp.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author hpg
 */
public class VPPManagementTest {


    VPPManagement vppManagement;

    @Before
    public void before() {
        InputStream stream = ResourceUtil.getStream("sToken_for_______________ .vpptoken");
        vppManagement = VPPManagement.create().setTransport(new ApacheHttpTransport()).setVPPToken(stream).build();
//        vppManagement = VPPManagement.create().setTransport(new ApacheHttpTransport()).setSTokens(sTokens).build();
    }

    @Test
    public void tokensTest() {
        String sTokens = "eyJleHBEYXRlIjoiMjAyMy0wNC0xMlQwODowMTo0NiswMDAwIiwidG9rZW4iOiJMOTVUWWw2OWp0dlF2Q1ZSajlxUjk2S2JZNVc5RFZ3c1pqYU1uQ2xGYWNJUDMzamRha0VuUzZrbW11ZndncVErYi96eGIreEtNcDdoWDltL2xkZ3loVXRoMnJ5aFdGaDNBSDhBOUpYVGtOL3R6ay93VkN3dEZveVNMNkFiak1xb0w3SnkzamdhWXNFVmpEbGxieEVKa0tSTmtJSzMwUWVNcVN1bzhYNzBtV2xOL2NxNGV4Ums4U2RIZzZIcWhYQ0ciLCJvcmdOYW1lIjoi5bm/5bee5biC5bu65LmU6Ieq5Yqo5YyW56eR5oqA5pyJ6ZmQ5YWs5Y+4In0=";
        STokens obj = JSON.parse(Base64.decodeStr(sTokens), STokens.class);
        System.out.println(obj);
    }

    @Test
    public void createVPPManagement() {
        InputStream stream = ResourceUtil.getStream("sToken_for_______________ .vpptoken");
        VPPManagement vpp = VPPManagement.create().setTransport(new ApacheHttpTransport()).setVPPToken(stream).build();
        System.out.println(vpp.getToken());
    }


    /**
     * {
     * "mdmInfo": {
     * "id": "522d5c43-44ca-4f7e-ba7a-53570cf60765",
     * "name": "Apple Configurator 2",
     * "metadata": "2.13.3"
     * },
     * "notificationAuthToken": "SUp3rS3Cr3t",
     * "notificationUrl": "https://www.next.com/notification",
     * "notificationTypes": [
     * "ASSET_COUNT",
     * "ASSET_MANAGEMENT",
     * "USER_MANAGEMENT",
     * "USER_ASSOCIATED"
     * ]
     * }
     * <p>
     * <p>
     * {notificationUrl=https://secb.appiron.net:31704/api/emm-command/common/apple/v1/notification, countryISO2ACode=CN, defaultPlatform=enterprisestore, uId=2178900000767387, locationName=广州市建乔自动化科技有限公司, tokenExpirationDate=2024-02-10T03:49:12+0000, websiteURL=https://business.apple.com, notificationAuthToken=SUp3rS3Cr3t, subscribedNotificationTypes=[USER_ASSOCIATED, USER_MANAGEMENT, ASSET_MANAGEMENT, ASSET_COUNT], apnToken=uaAFJgkkxbYKLO5R5j7yhhgxlykTfIvYfhwZa6xYmao=}
     */
    @Test
    public void clientConfig() throws Exception {
        VPPManagementVersion2Api api = vppManagement.api();
        ClientConfigRequest request = new ClientConfigRequest();
        request.setNotificationTypes(Arrays.asList("ASSET_COUNT", "ASSET_MANAGEMENT", "USER_MANAGEMENT", "USER_ASSOCIATED"));
        request.setNotificationUrl("https://secb.appiron.net:31704/api/emm-command/common/apple/v1/notification");
        request.setNotificationAuthToken("SUp3rS3Cr3t");
        MdmInfo mdmInfo = new MdmInfo();
        mdmInfo.setId("522d5c43-44ca-4f7e-ba7a-53570cf60765");
        mdmInfo.setName("Apple Configurator 2");
        mdmInfo.setMetadata("2.13.3");
        request.setNotificationAuthToken("SUp3rS3Cr3t");
        ClientConfigResponse clientConfigResponse = api.clientConfig(request);
        System.out.println(clientConfigResponse);
    }

    @Test
    public void severConfig() throws Exception {
        VPPManagementVersion2Api api = vppManagement.api();
        ServiceConfigResponse serviceConfig = api.serviceConfig(1);
        System.out.println(JSON.toJSON(serviceConfig));
    }


    @Test
    public void getLicensesSrvUrl() throws Exception {
        VPPManagementVersion2Api api = vppManagement.api();
        ClientConfigResponse clientConfigResponse = api.clientConfig(new ClientConfigRequest());
        System.out.println(clientConfigResponse);
    }

    @Test
    public void getAppBookInformation() throws Exception {
        // version=2&id=361309726&p=mdm-lockup&caller=MDM&platform=enterprisestore&cc=us&l=en
        VPPManagementVersion2Api api = vppManagement.api();
        AppBookInformationRequest request = new AppBookInformationRequest("361309726");
//        request.put("version", 2);
//        request.put("id", "361309726");
//        request.put("p", "mdm-lockup");
//        request.put("caller", "MDM");
//        request.put("platform", "enterprisestore");
//        request.put("cc", "us");
//        request.put("l", "en");
        AppBookInformationResponse response = api.getAppBookInformation(request);
        System.out.println(response);
    }


    @Test
    public void getAssets() throws Exception {
        //  example: ?pageIndex=0&pricingParam=STDQ&productType=App
        VPPManagementVersion2Api api = vppManagement.api();
        GetAssetsRequest request = new GetAssetsRequest();
//        request.setPageIndex(0).setPricingParam("STDQ").setProductType("App");
//        request.setAdamId("1235504705");
        request.setAdamId("387109554");
        GetAssetsResponse response = api.getAssets(request);
        System.out.println(response.toJson());
    }

    @Test
    public void associateAssets() throws Exception {
        // example: ?pageIndex=0&pricingParam=STDQ&productType=App
        VPPManagementVersion2Api api = vppManagement.api();
        ManageAssetsRequest request = new ManageAssetsRequest();
        Asset asset = new Asset();
        asset.setPricingParam("STDQ");
        asset.setAdamId("1044283059");
        request.setSerialNumbers(Collections.singletonList("123123"));
        request.setAssets(Collections.singletonList(asset));
        EventResponse response = api.associateAssets(request);
        System.out.println(response);
    }

    @Test
    public void disassociateAssets() throws Exception {
        // example: ?pageIndex=0&pricingParam=STDQ&productType=App
        VPPManagementVersion2Api api = vppManagement.api();
        ManageAssetsRequest request = new ManageAssetsRequest();
        Asset asset = new Asset();
        asset.setPricingParam("STDQ");
        asset.setAdamId("408709785");
        request.setClientUserIds(Arrays.asList("client-1", "client-2"));
        request.setClientUserIds(Arrays.asList("serial-1", "serial-2"));
        request.setAssets(Collections.singletonList(asset));
        EventResponse response = api.disassociateAssets(request);
        System.out.println(response);
    }

    @Test
    public void revokeAssets() throws Exception {
        //  example: ?pageIndex=0&pricingParam=STDQ&productType=App
        VPPManagementVersion2Api api = vppManagement.api();
        RevokeAssetsRequest request = new RevokeAssetsRequest();
        Asset asset = new Asset();
        asset.setPricingParam("STDQ");
        asset.setAdamId("408709785");
        request.setClientUserIds(Arrays.asList("client-1", "client-2"));
        request.setClientUserIds(Arrays.asList("serial-1", "serial-2"));
        EventResponse response = api.revokeAssets(request);
        System.out.println(response);
    }

    @Test
    public void getAssignments() throws Exception {
        //  example: ?adamId=408709785
        VPPManagementVersion2Api api = vppManagement.api();
        GetAssignmentsRequest request = new GetAssignmentsRequest();
//        request.setAdamId("1235504705");
        System.out.println(api.getAssignments(request));
    }


    @Test
    public void getUsers() throws Exception {
        //        ?activeOnly=true
        VPPManagementVersion2Api api = vppManagement.api();
        GetUsersRequest request = new GetUsersRequest();
//        request.setActiveOnly(true);
        System.out.println(api.getUsers(request).toJson());
    }

    @Test
    public void createUsers() throws Exception {
        //        ?adamId=408709785
        VPPManagementVersion2Api api = vppManagement.api();
        ManageUsersRequest request = new ManageUsersRequest();
        RequestUser user1 = new RequestUser();
        user1.setClientUserId("client-100");
        user1.setEmail("client-100@next.com");
        user1.setManagedAppleId("maid-100@next.com");
        RequestUser user2 = new RequestUser();
        user2.setClientUserId("client-101");
        user2.setEmail("client-101@next.com");
        user2.setManagedAppleId("maid-101@next.com");
        RequestUser user3 = new RequestUser();
        user3.setClientUserId("client-102");
        user3.setEmail("client-102@next.com");
        request.setUsers(Arrays.asList(user1, user2, user3));
        System.out.println(api.createUsers(request));
    }


    @Test
    public void updateUsers() throws Exception {
        //        ?adamId=408709785
        VPPManagementVersion2Api api = vppManagement.api();
        ManageUsersRequest request = new ManageUsersRequest();
        RequestUser user1 = new RequestUser();
        user1.setClientUserId("client-100");
        user1.setEmail("client-100@next.com");
        user1.setManagedAppleId("maid-100@next.com");
        RequestUser user2 = new RequestUser();
        user2.setClientUserId("client-101");
        user2.setEmail("client-101@next.com");
        user2.setManagedAppleId("maid-101@next.com");
        RequestUser user3 = new RequestUser();
        user3.setClientUserId("client-102");
        user3.setEmail("client-102@next.com");
        request.setUsers(Arrays.asList(user1, user2, user3));
        System.out.println(api.updateUsers(request));
    }


    @Test
    public void retireUsers() throws Exception {
        //        ?adamId=408709785
        VPPManagementVersion2Api api = vppManagement.api();
        ManageUsersRequest request = new ManageUsersRequest();
        RequestUser user1 = new RequestUser();
        user1.setClientUserId("client-100");
        RequestUser user2 = new RequestUser();
        user2.setClientUserId("client-101");
        RequestUser user3 = new RequestUser();
        user3.setClientUserId("client-102");
        request.setUsers(Arrays.asList(user1, user2, user3));
        System.out.println(api.retireUsers(request));
    }

    @Test
    public void eventStatus() throws Exception {
        //        ?adamId=408709785
        VPPManagementVersion2Api api = vppManagement.api();
        System.out.println(api.eventStatus("73f2e7d8-de18-41ef-85b8-fe1052f1a76c"));
    }
}
