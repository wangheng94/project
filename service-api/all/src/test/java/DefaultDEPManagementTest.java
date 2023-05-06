import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import com.appiron.appleservices.common.api.ApacheHttpTransport;
import cn.hutool.core.lang.Assert;
import com.appiron.appleservices.dep.*;
import com.appiron.appleservices.util.ByPasscodeUtil;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

/**
 * @author hpg
 */
public class DefaultDEPManagementTest {

    String content = "{\"consumer_key\":\"CK_922928ab4cadf4c7c09ba262a32c039416d1e7c7a31f1fec4957b0025fa4c1b6fb4567ea5631d5daab579f6777e1959d\",\"consumer_secret\":\"CS_fd42d759a59f6d7d8a84eceaf9457a9646ac141a\",\"access_token\":\"AT_O18207167704Of09929305c4b1d98a104efd6c3d12a6527059e4dO1678088932362\",\"access_secret\":\"AS_4aca9d39140e261716a15c8dd0ccc9e09a24ef40\",\"access_token_expiry\":\"2024-03-05T07:48:52Z\"}";

    DEPManagement depManagement;

    @Before
    public void before() throws Exception {
        depManagement = DEPManagement.create()
                .setToken(content)
//                .setCert(ResourceUtil.getResourceInputStream("cloud30/30 MDM_Token_2023-02-02T06-29-29Z_smime.p7m"))
//                .setKey(ResourceUtil.getResourceInputStream("cloud30/ca.key"), "123456")
//                .setTransport(new ApacheHttpTransport())
                .setTransport(new VertxHttpTransport())
                .build();
    }


    @Test
    public void build() throws Exception {
    }

    @Test
    public void token() throws Exception {
        System.out.println(depManagement.isExpire());
    }

    /**
     * 1673415358308O1O1FAB7CED20389BD3D22E12C85FD15C04O27309A20384C4AC48109DA63B6A229ADO28502085O11Op1OC534F678F757126B9B2BA0377FB05CABC9039798OF5DF6FF211A02DD53AB71EE0D8E28A53
     */
    @Test
    public void sessionToken() throws Exception {
        AbstractDEPManagementApi api = depManagement.api();
        AuthSessionTokenResponse authSessionTokenResponse = api.authSessionToken();
        Assert.notNull(authSessionTokenResponse);
        Assert.notNull(authSessionTokenResponse.getAuthSessionToken());
    }


    @Test
    public void account() throws Exception {
        AccountResponse accountResponse = depManagement.api().account();
        Assert.notNull(accountResponse);
        Assert.notEmpty(accountResponse.getServerName());
        Assert.notEmpty(accountResponse.getServerUuid());
        Assert.notEmpty(accountResponse.getFacilitatorId());
        Assert.notEmpty(accountResponse.getAdminId());
        Assert.notEmpty(accountResponse.getOrgName());
        Assert.notEmpty(accountResponse.getOrgEmail());
        Assert.notEmpty(accountResponse.getOrgPhone());
        Assert.notEmpty(accountResponse.getOrgAddress());
        Assert.notEmpty(accountResponse.getOrgId());
        Assert.notEmpty(accountResponse.getOrgIdHash());
        Assert.notEmpty(accountResponse.getOrgType());
        Assert.notEmpty(accountResponse.getOrgVersion());
        Assert.notEmpty(accountResponse.getUrls());
        System.out.println(accountResponse.toJson());
    }

    @Test
    public void defineProfile() throws Exception {
        DefineProfileRequest request = new DefineProfileRequest();
        request.setUrl("https://www.example.com");
        request.setProfileName("测试API");
        request.setDevices(Collections.singletonList("C6KDV0MJ0GQW"));
        // DefineProfileResponse(devices=null, profileUUID=AAD54C578AD0C7E0E213059881A49DB4)
        DEPManagementApi api = depManagement.api();
        DefineProfileResponse defineProfileResponse = api.defineProfile(request);
        System.out.println(defineProfileResponse);
    }

    @Test
    public void getProfile() throws Exception {
        GetProfileRequest request = new GetProfileRequest();
        request.setProfileUUID("17B71A7ACE08DDD2D661B5D1738A3534");
//        request.setProfileUUID("A59FCCF3984A7213928E09095F96A0AF");
        DEPManagementApi api = depManagement.api();
        GetProfileResponse profile = api.getProfile(request);
        System.out.println(profile.toJson());
    }

    @Test
    public void assignProfile() throws Exception {
        ProfileServiceRequest request = new ProfileServiceRequest();
//        request.setProfileUUID("AAD54C578AD0C7E0E213059881A49DB4");
//        request.setProfileUUID("XXXXXXXXXXXXXXXXXXXXXXXX");
        request.setProfileUUID("17B71A7ACE08DDD2D661B5D1738A3534");
        request.setDevices(new String[]{"C6KDV0MJ0GQW", "8848"});
        System.out.println(depManagement.api().assignProfile(request).toJson());
    }

    @Test
    public void removeProfile() throws Exception {
        ClearProfileRequest request = new ClearProfileRequest();
        request.setProfileUUID("AAD54C578AD0C7E0E213059881A49DB4");
        request.setDevices(new String[]{"9527", "8848"});
        // DefineProfileResponse(devices=null, profileUUID=AAD54C578AD0C7E0E213059881A49DB4)
        System.out.println(depManagement.api().removeProfile(request).toJson());
    }


    @Test
    public void deviceDetails() throws Exception {
        DeviceListRequest request = new DeviceListRequest();
        request.setDevices(Arrays.asList("9527", "8848"));
        // DefineProfileResponse(devices=null, profileUUID=AAD54C578AD0C7E0E213059881A49DB4)
        DEPManagementApi api = depManagement.api();
        System.out.println(api.deviceDetails(request).toJson());
    }


    @Test
    public void listDevices() throws Exception {
        FetchDeviceRequest request = new FetchDeviceRequest();
        request.setLimit(50);
        FetchDeviceResponse fetchDeviceResponse = null;
        // DefineProfileResponse(devices=null, profileUUID=AAD54C578AD0C7E0E213059881A49DB4)
        do {
            fetchDeviceResponse = depManagement.api().listDevices(request);
            System.out.println(fetchDeviceResponse.toJson());
            request.setCursor(fetchDeviceResponse.getCursor());
        } while (fetchDeviceResponse.getMoreToFollow());
    }


    @Test
    public void syncListDevices() throws Exception {
        DEPManagementApi api = depManagement.api();
        FetchDeviceRequest fetchDeviceRequest = new FetchDeviceRequest();
        fetchDeviceRequest.setLimit(50);
        // DefineProfileResponse(devices=null, profileUUID=AAD54C578AD0C7E0E213059881A49DB4)
        FetchDeviceResponse listDevicesResponse = api.listDevices(fetchDeviceRequest);
        System.out.println(listDevicesResponse.toJson());


        SyncDeviceRequest request = new SyncDeviceRequest();
        request.setLimit(fetchDeviceRequest.getLimit());
        request.setCursor(listDevicesResponse.getCursor());

        FetchDeviceResponse fetchDeviceResponse = null;
        do {
            fetchDeviceResponse = api.syncListDevices(request);
            System.out.println(fetchDeviceResponse.toJson());

            request.setLimit(fetchDeviceRequest.getLimit());
            request.setCursor(fetchDeviceResponse.getCursor());

        } while (fetchDeviceResponse.getMoreToFollow());
    }


    @Test
    public void disownDevices() throws Exception {
        DeviceListRequest request = new DeviceListRequest();
        request.setDevices(Arrays.asList("9527", "8848"));
        // DefineProfileResponse(devices=null, profileUUID=AAD54C578AD0C7E0E213059881A49DB4)
        System.out.println(depManagement.api().disownDevices(request).toJson());
    }

    @Test
    public void activationLockDevices() throws Exception {
        ActivationLockRequest request = new ActivationLockRequest();

        String password = ByPasscodeUtil.generateRandomPassword(16);
        String escrowKey = ByPasscodeUtil.createEscrowKey(password).toUpperCase(Locale.ROOT);
        String code = ByPasscodeUtil.createCode(password);

        System.out.println("password:" + password);
        System.out.println("escrowKey:" + escrowKey);
        System.out.println("code:" + code);


        request.setDevice("xxxxxxxxxxx");
        request.setEscrowKey(escrowKey);

        System.out.println(depManagement.api().activationLockDevices(request).toJson());
    }

    @Test
    public void escrowKeyUnlockService() throws Exception {
        EscrowKeyUnlockService unlockService = new EscrowKeyUnlockService.Builder().cert(new byte[0]).password("xxxxxx").build();

        EscrowKeyUnlockStatusResponse response = unlockService.escrowKeyUnlock(null, null, null, null,
                null, null, null, null, null);

        System.out.println(response.getStatusCode());
        System.out.println(response.getContent());
        System.out.println(response.info());
    }
}
