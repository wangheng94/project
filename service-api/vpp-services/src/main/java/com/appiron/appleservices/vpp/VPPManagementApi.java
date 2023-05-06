package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.api.AbstractHttpRequest;
import com.appiron.appleservices.common.api.AbstractHttpResponse;
import com.appiron.appleservices.common.api.HttpMethods;
import com.appiron.appleservices.common.api.HttpTransport;
import cn.hutool.core.io.IoUtil;
import com.appiron.appleservices.common.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hpg
 */
@Deprecated
public class VPPManagementApi implements IVPPManagementApi {

    public final VPPManagement vppManagement;
    public final HttpTransport transport;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultResponseHandler.class);

    public VPPManagementApi(VPPManagement vppManagement, HttpTransport transport) {
        this.vppManagement = vppManagement;
        this.transport = transport;
    }


    private <T> T handler(String url, AbstractHttpRequest request, AbstractHttpResponse response, Class<T> clazz) throws IOException {
        String statusLine = response.getStatusLine();
        int statusCode = response.getStatusCode();
        LOGGER.info("Status:" + statusLine);
        if (statusCode == 200) {
            String content = IoUtil.read(response.getContent(), StandardCharsets.UTF_8);
            return JSON.parse(content, clazz);
        } else {
            String msg = IoUtil.read(response.getContent(), StandardCharsets.UTF_8);
            throw new RuntimeException(msg);
        }
    }

    @Override
    public Object getUsersSrv() throws IOException {
        String url = VPPServiceURL.VERSION_1.getUsersSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object getLicensesSrvUrl() throws IOException {
        String url = VPPServiceURL.VERSION_1.getLicensesSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object editUserSrv() throws IOException {
        String url = VPPServiceURL.VERSION_1.editUserSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object retireUserSrv() throws IOException {
        String url = VPPServiceURL.VERSION_1.retireUserSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object getAssignmentsSrv() throws IOException {
        String url = VPPServiceURL.VERSION_1.getAssignmentsSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object disassociateLicenseSrv() throws IOException {
        String url = VPPServiceURL.VERSION_1.disassociateLicenseSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object registerUserSrv() throws IOException {
        String url = VPPServiceURL.VERSION_1.registerUserSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object associateLicenseSrv() throws IOException {
        String url = VPPServiceURL.VERSION_1.associateLicenseSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object getVPPAssetsSrv() throws IOException {
        String url = VPPServiceURL.VERSION_1.getVPPAssetsSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.GET, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object vppWebsite() throws IOException {
        String url = VPPServiceURL.VERSION_1.vppWebsiteUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.GET, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object getUserSrv() throws IOException {
        String url = VPPServiceURL.VERSION_1.getUserSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object contentMetadataLookup(int version, String id, String p, String caller, String platform, String cc, String l) throws IOException {
        String url = VPPServiceURL.VERSION_1.contentMetadataLookupUrl;
        Map<String, Object> params = new HashMap<>();
        params.put("version", version);
        params.put("id", id);
        params.put("p", p);
        params.put("caller", caller);
        params.put("platform", platform);
        params.put("cc", cc);
        params.put("l", l);
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.GET, params, url);
        AbstractHttpResponse response = request.execute();
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object clientConfigSrv() throws IOException {
        String url = VPPServiceURL.VERSION_1.clientConfigSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object manageVPPLicensesByAdamIdSrv() throws IOException {
        String url = VPPServiceURL.VERSION_1.manageVPPLicensesByAdamIdSrvUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }

    @Override
    public Object invitationEmail() throws IOException {
        String url = VPPServiceURL.VERSION_1.invitationEmailUrl;
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.POST, url);
        AbstractHttpResponse response = request.execute();
        request.setContent("{\"sToken\": \"" + vppManagement.getSTokens() + "\"}");
        return handler(url, request, response, HashMap.class);
    }
}
