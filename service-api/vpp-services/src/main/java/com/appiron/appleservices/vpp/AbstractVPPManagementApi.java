package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.api.AbstractHttpRequest;
import com.appiron.appleservices.common.api.AbstractHttpResponse;
import com.appiron.appleservices.common.api.HttpMethods;
import com.appiron.appleservices.common.api.HttpTransport;
import com.appiron.appleservices.common.ResponseHandler;

import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author hpg
 */
public abstract class AbstractVPPManagementApi implements IConfigurationManagement, IAssetManagement, IUserManagement, IEventManagement {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractVPPManagementApi.class);

    private final VPPManagement vppManagement;

    private final HttpTransport transport;

    private final ResponseHandler handler;

    private final static String CONTENT_TYPE_OF_APP_JSON_UTF_8 = "application/json; charset=utf-8";

    protected AbstractVPPManagementApi(VPPManagement vppManagement, HttpTransport transport) {
        this.vppManagement = vppManagement;
        this.transport = transport;
        this.handler = new DefaultResponseHandler();
    }

    protected AbstractVPPManagementApi(VPPManagement vppManagement, HttpTransport transport, ResponseHandler handler) {
        this.vppManagement = vppManagement;
        this.transport = transport;
        this.handler = handler;
    }

    private <T> T handler(String url, AbstractHttpRequest request, AbstractHttpResponse response, Class<T> clazz) throws Exception {
        return handler.handler(url, request, response, clazz);
    }

    public String sTokens() {
        return vppManagement.getSTokens();
    }

    @Override
    public ClientConfigResponse clientConfig(ClientConfigRequest request) throws Exception {
        String url = VPPServiceURL.VERSION_2.CLIENT_CONFIG;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.POST, url);
        httpRequest.addHeader("Authorization", "Bearer " + sTokens());
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
        httpRequest.setContent(request.toJson());
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, ClientConfigResponse.class);
    }

    @Override
    public ServiceConfigResponse serviceConfig(int version) throws Exception {
        String url = VPPServiceURL.VERSION_2.SERVICE_CONFIG;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.GET, url);
        httpRequest.setContent(version + "");
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, ServiceConfigResponse.class);
    }

    @Override
    public AppBookInformationResponse getAppBookInformation(AppBookInformationRequest request) throws Exception {
        String url = VPPServiceURL.VERSION_2.CONTENT_META_DATA_LOOKUP;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.GET, request.toParams(), url);
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, AppBookInformationResponse.class);
    }


    @Override
    public GetAssetsResponse getAssets(GetAssetsRequest request) throws Exception {
        String url = VPPServiceURL.VERSION_2.ASSETS;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.GET, request.toParams(), url);
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
        httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + sTokens());
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, GetAssetsResponse.class);
    }

    @Override
    public EventResponse associateAssets(ManageAssetsRequest request) throws Exception {
        String url = VPPServiceURL.VERSION_2.ASSOCIATE_ASSETS;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.POST, url);
        httpRequest.setContent(request.toJson());
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
        httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + sTokens());
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, EventResponse.class);
    }

    @Override
    public EventResponse disassociateAssets(ManageAssetsRequest request) throws Exception {
        String url = VPPServiceURL.VERSION_2.DISASSOCIATE;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.POST, url);
        httpRequest.setContent(request.toJson());
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
        httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + sTokens());
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, EventResponse.class);
    }

    @Override
    public EventResponse revokeAssets(RevokeAssetsRequest request) throws Exception {
        String url = VPPServiceURL.VERSION_2.REVOKE_ASSETS;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.POST, url);
        httpRequest.setContent(request.toJson());
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
        httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + sTokens());
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, EventResponse.class);
    }

    @Override
    public GetAssignmentsResponse getAssignments(GetAssignmentsRequest request) throws Exception {
        String url = VPPServiceURL.VERSION_2.ASSIGNMENTS;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.GET, request.toParams(), url);
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
        httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + sTokens());
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, GetAssignmentsResponse.class);
    }

    @Override
    public GetUsersResponse getUsers(GetUsersRequest request) throws Exception {
        String url = VPPServiceURL.VERSION_2.USERS;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.GET, request.toParams(), url);
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
        httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + sTokens());
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, GetUsersResponse.class);
    }

    @Override
    public ManageUsersResponse createUsers(ManageUsersRequest request) throws Exception {
        String url = VPPServiceURL.VERSION_2.CREATE_USERS;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.POST, request.toParams(), url);
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
        httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + sTokens());
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, ManageUsersResponse.class);
    }

    @Override
    public ManageUsersResponse updateUsers(ManageUsersRequest request) throws Exception {
        String url = VPPServiceURL.VERSION_2.UPDATE_USERS;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.POST, request.toParams(), url);
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
        httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + sTokens());
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, ManageUsersResponse.class);
    }

    @Override
    public ManageUsersResponse retireUsers(ManageUsersRequest request) throws Exception {
        String url = VPPServiceURL.VERSION_2.RETIRE_USERS;
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.POST, request.toParams(), url);
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
        httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + sTokens());
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, ManageUsersResponse.class);
    }

    @Override
    public StatusResponse eventStatus(String eventId) throws Exception {
        String url = VPPServiceURL.VERSION_2.STATUS;
        HashMap<String, Object> params = new HashMap<>();
        params.put("eventId", eventId);
        AbstractHttpRequest httpRequest = transport.buildRequest(HttpMethods.GET, params, url);
        httpRequest.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
        httpRequest.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + sTokens());
        AbstractHttpResponse httpResponse = httpRequest.execute();
        return handler(url, httpRequest, httpResponse, StatusResponse.class);
    }
}
