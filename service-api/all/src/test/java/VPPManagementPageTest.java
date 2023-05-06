import com.appiron.appleservices.common.api.ApacheHttpTransport;
import cn.hutool.core.io.resource.ResourceUtil;
import com.appiron.appleservices.vpp.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author hpg
 */
public class VPPManagementPageTest {


    VPPManagement vppManagement;

    @Before
    public void before() {
        InputStream stream = ResourceUtil.getStream("sToken_for_______________ .vpptoken");
        vppManagement = VPPManagement.create().setTransport(new ApacheHttpTransport()).setVPPToken(stream).build();
    }

    /**
     * 553bb3d0-a92a-11ed-8786-018f29ee9c9c
     *
     * @throws Exception
     */
    @Test
    public void getAssets() throws Exception {
//        ?pageIndex=0&pricingParam=STDQ&productType=App
        VPPManagementVersion2Api api = vppManagement.api();
        GetAssetsRequest request = new GetAssetsRequest();
        GetAssetsResponse response = null;
        do {
            response = api.getAssets(request);
            System.out.println(response.toJson());
            request.setPageIndex(response.getNextPageIndex());
        } while (response.getNextPageIndex() != null);
    }

    @Test
    public void getAssignments() throws Exception {
        //        ?adamId=408709785
        VPPManagementVersion2Api api = vppManagement.api();
        GetAssignmentsRequest request = new GetAssignmentsRequest();
//        request.setAdamId("1235504705");
        GetAssignmentsResponse response = null;
        do {
            response = api.getAssignments(request);
            System.out.println(response.toJson());
            request.setPageIndex(response.getNextPageIndex());
        } while (response.getNextPageIndex() != null);

    }

    @Test
    public void getUsers() throws Exception {
        //        ?adamId=408709785
        VPPManagementVersion2Api api = vppManagement.api();
        GetUsersRequest request = new GetUsersRequest();
//        request.setActiveOnly(true);
        GetUsersResponse response = null;
        do {
            response = api.getUsers(request);
            System.out.println(response.toJson());
            request.setPageIndex(response.getNextPageIndex());
        } while (response.getNextPageIndex() != null);

    }

}