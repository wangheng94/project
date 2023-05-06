package com.appiron.appleservices.dep;


import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.appiron.appleservices.common.GenerationConvert;
import com.appiron.appleservices.common.ResponseHandler;
import com.appiron.appleservices.common.api.AbstractHttpRequest;
import com.appiron.appleservices.common.api.AbstractHttpResponse;
import com.appiron.appleservices.common.api.HttpMethods;
import com.appiron.appleservices.common.api.HttpTransport;
import com.appiron.appleservices.common.hutool.cache.Cache;
import com.appiron.appleservices.common.hutool.cache.impl.TimedCache;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

/**
 * @author hpg
 */
public class AbstractDEPManagementApi implements IDEPManagementApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDEPManagementApi.class);

    private final DEPManagement depManagement;

    private final HttpTransport transport;

    private final ResponseHandler handler;

    private final static String CACHE_TOKEN_KEY = "SESSION";

    /**
     * Request header X-Server-Protocol-Version
     */
    private final static String HEADER_SERVER_PROTOCOL_VERSION = "X-Server-Protocol-Version";

    /**
     * Request header X-ADM-Auth-Session
     */
    private final static String HEADER_SESSION_AUTH_TOKEN = "X-ADM-Auth-Session";

    private final static String CONTENT_TYPE_OF_APP_JSON_UTF_8 = "application/json; charset=utf-8";

    private final static String SERVER_PROTOCOL_VERSION_V1 = "1";

    private final static String SERVER_PROTOCOL_VERSION_V2 = "2";

    private final static String SERVER_PROTOCOL_VERSION_V3 = "3";

    private final static String OAUTH_VERSION = "1.0";

    private final static String OAUTH_SIGNATURE_METHOD = "HMAC-SHA1";

    private final static String OAUTH_REALM = "OAuth realm";

    private final static String ADM = "ADM";

    private final static String OAUTH_CONSUMER_KEY = "oauth_consumer_key";

    private final static String OAUTH_NONCE_KEY = "oauth_nonce";

    private final static String OAUTH_SIGNATURE_METHOD_KEY = "oauth_signature_method";

    private final static String OAUTH_SIGNATURE_KEY = "oauth_signature";

    private final static String OAUTH_TIMESTAMP_KEY = "oauth_timestamp";

    private final static String OAUTH_TOKEN_KEY = "oauth_token";

    private final static String OAUTH_VERSION_KEY = "oauth_version";

    private final static String CHARSET_UTF_8 = "UTF-8";

    private final static String METHOD_OF_GET = "GET";

    private final Cache<String, String> TOKEN_CACHE = new TimedCache<>(1000 * 60);

    /**
     * 记录认证失败次数
     */
    private final AtomicInteger authFailedTimes = new AtomicInteger(0);

    public int currentTimes() {
        return authFailedTimes.get();
    }

    public int incrTimes() {
        return authFailedTimes.incrementAndGet();
    }

    public void reSet() {
        authFailedTimes.set(0);
    }

    public AbstractDEPManagementApi(DEPManagement depManagement, HttpTransport transport) {
        this.depManagement = depManagement;
        this.transport = transport;
        this.handler = new DefaultResponseHandler(this);
    }

    public AbstractDEPManagementApi(DEPManagement depManagement, HttpTransport transport, ResponseHandler handler) {
        this.depManagement = depManagement;
        this.transport = transport;
        this.handler = handler;
    }

    /**
     * 获取请求头的Authentication的值
     *
     * @param oauth     MDM服务器令牌解析结果
     * @param signature 签名
     * @param nonce     随机值
     * @param timeStamp 时间戳
     * @return authentication内容值
     */
    private String buildAuthentication(Oauth oauth, String signature, String nonce, long timeStamp) {
        String QUOTATION_MARK = "\"";
        //  OAuth realm="ADM",
        return OAUTH_REALM + "=" + QUOTATION_MARK + ADM + QUOTATION_MARK + "," +
                //oauth_consumer_key="CK_00fadb3d36c6094cf479838455321b7c",
                OAUTH_CONSUMER_KEY + "=" + QUOTATION_MARK + oauth.getConsumerKey() + QUOTATION_MARK + "," +
                //oauth_token="AT_O2109279022Oe03b641fd6f07d7face7894211d521fd8bef09c3O137392",
                OAUTH_TOKEN_KEY + "=" + QUOTATION_MARK + oauth.getAccessToken() + QUOTATION_MARK + "," +
                //oauth_signature_method="HMAC-SHA1",
                OAUTH_SIGNATURE_METHOD_KEY + "=" + QUOTATION_MARK + OAUTH_SIGNATURE_METHOD + QUOTATION_MARK + "," +
                //oauth_timestamp="1111111111111",
                OAUTH_TIMESTAMP_KEY + "=" + QUOTATION_MARK + timeStamp + QUOTATION_MARK + "," +
                //oauth_nonce="112323123123123123123",
                OAUTH_NONCE_KEY + "=" + QUOTATION_MARK + nonce + QUOTATION_MARK + "," +
                //oauth_version="1.0",
                OAUTH_VERSION_KEY + "=" + QUOTATION_MARK + OAUTH_VERSION + QUOTATION_MARK + "," +
                //oauth_signature="XXN4PkXYro2r8n%2FDVarVZiy5BJY%3D"
                OAUTH_SIGNATURE_KEY + "=" + QUOTATION_MARK + signature + QUOTATION_MARK;
    }


    /**
     * 获取签名
     *
     * @param authTokenUrl 认证URL
     * @param oauth        MDM服务器令牌解析结果
     * @param nonce        随机值
     * @param timeStamp    时间
     * @return Oauth签名
     */
    private String getOauthSignature(String authTokenUrl, Oauth oauth, String nonce, long timeStamp) throws UnsupportedEncodingException {
        String encodeKey = oauth.getConsumerSecret() + "&" + oauth.getAccessSecret();
        String encodeUrl = URLEncoder.encode(authTokenUrl, CHARSET_UTF_8);
        String paramBuilder = OAUTH_CONSUMER_KEY + "=" + oauth.getConsumerKey() +
                "&" + OAUTH_NONCE_KEY + "=" + nonce +
                "&" + OAUTH_SIGNATURE_METHOD_KEY + "=" + OAUTH_SIGNATURE_METHOD +
                "&" + OAUTH_TIMESTAMP_KEY + "=" + timeStamp +
                "&" + OAUTH_TOKEN_KEY + "=" + oauth.getAccessToken() +
                "&" + OAUTH_VERSION_KEY + "=" + OAUTH_VERSION;
        String encodeParam = URLEncoder.encode(paramBuilder, CHARSET_UTF_8);
        String waitSha1EncodeStr = METHOD_OF_GET + "&" + encodeUrl + "&" + encodeParam;
        byte[] signBytes = hmacSHA1Encrypt(waitSha1EncodeStr, encodeKey);
        String encode = Base64.encode(signBytes);
        return URLEncoder.encode(encode, CHARSET_UTF_8);
    }

    public static byte[] hmacSHA1Encrypt(String encryptText, String encryptKey) {
        byte[] data;
        try {
            data = encryptKey.getBytes(StandardCharsets.UTF_8);
            SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
            byte[] text = encryptText.getBytes(StandardCharsets.UTF_8);
            return mac.doFinal(text);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String refreshSessionToken() throws Exception {
        String authSessionToken = authSessionToken().getAuthSessionToken();
        TOKEN_CACHE.put(CACHE_TOKEN_KEY, authSessionToken);
        return authSessionToken;
    }

    @Override
    public String updateSessionToken(String token) {
        TOKEN_CACHE.put(CACHE_TOKEN_KEY, token);
        return token;
    }

    @Override
    public String sessionToken() throws Exception {
        String session = TOKEN_CACHE.get(CACHE_TOKEN_KEY);
        if (Objects.isNull(session)) {
            session = refreshSessionToken();
        }
        return session;
    }

    private <T extends GenerationConvert> T handler(String url, AbstractHttpRequest request, AbstractHttpResponse response, Class<T> clazz) throws Exception {
        return this.handler.handler(url, request, response, clazz);
    }

    private <T extends GenerationConvert> T handler(String url, AbstractHttpRequest request, AbstractHttpResponse response, Class<T> clazz, Function<String, AbstractHttpResponse> function) throws Exception {
        return this.handler.handler(url, request, response, clazz, function);
    }

    private <T extends GenerationConvert> T handler(AbstractHttpRequest request, AbstractHttpResponse response, Class<T> clazz, Function<String, AbstractHttpResponse> function) throws Exception {
        return this.handler.handler(request.getUrl(), request, response, clazz, function);
    }

    private void setHeaders(AbstractHttpRequest request, String serverProtocolVersionV2) throws Exception {
        request.addHeader(HEADER_SERVER_PROTOCOL_VERSION, serverProtocolVersionV2);
        request.addHeader(HEADER_SESSION_AUTH_TOKEN, sessionToken());
        request.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);
    }

    @Override
    public AuthSessionTokenResponse authSessionToken() throws Exception {

        String url = ServiceURL.SESSION;
        Oauth oauth = depManagement.getOauth();
        String nonce = RandomUtil.randomNumbers(32);
        long timeStamp = DateUtil.currentSeconds();
        String oauthSignature = getOauthSignature(url, oauth, nonce, timeStamp);
        LOGGER.debug("signature:{}", oauthSignature);

        String authentication = buildAuthentication(oauth, oauthSignature, nonce, timeStamp);
        LOGGER.debug("Authentication:{}", authentication);

        AbstractHttpRequest request = getRequest(url, HttpMethods.GET);
        request.addHeader(HttpHeaders.AUTHORIZATION, authentication);
        AbstractHttpResponse response = request.execute();
        return handler(url, request, response, AuthSessionTokenResponse.class);
    }

    @Override
    public AccountResponse account() throws Exception {
        AbstractHttpRequest request = getRequest(ServiceURL.ACCOUNT, HttpMethods.GET);
        setHeaders(request, SERVER_PROTOCOL_VERSION_V3);
        AbstractHttpResponse response = request.execute();
        return handler(request, response, AccountResponse.class, null);
    }

    @Override
    public DefineProfileResponse defineProfile(DefineProfileRequest defineProfileRequest) throws Exception {
        AbstractHttpRequest request = getRequest(ServiceURL.PROFILE, HttpMethods.POST);
        request.setContent(defineProfileRequest.toJson());
        setHeaders(request, SERVER_PROTOCOL_VERSION_V2);
        AbstractHttpResponse response = request.execute();
        return handler(request, response, DefineProfileResponse.class, null);
    }

    @Override
    public GetProfileResponse getProfile(GetProfileRequest getProfileRequest) throws Exception {
        Map<String, Object> params = getProfileRequest.toParams();
        AbstractHttpRequest request = transport.buildRequest(HttpMethods.GET, params, ServiceURL.PROFILE);
        setHeaders(request, SERVER_PROTOCOL_VERSION_V2);
        AbstractHttpResponse response = request.execute();
        return handler(request, response, GetProfileResponse.class, null);
    }

    @Override
    public AssignProfileResponse assignProfile(ProfileServiceRequest profileServiceRequest) throws Exception {
        AbstractHttpRequest request = getRequest(ServiceURL.PROFILE_DEVICES, HttpMethods.POST);
        request.setContent(profileServiceRequest.toJson());
        setHeaders(request, SERVER_PROTOCOL_VERSION_V2);
        AbstractHttpResponse response = request.execute();
        return handler(request, response, AssignProfileResponse.class, null);
    }

    @Override
    public ClearProfileResponse removeProfile(ClearProfileRequest clearProfileRequest) throws Exception {
        AbstractHttpRequest request = getRequest(ServiceURL.PROFILE_DEVICES, HttpMethods.POST);
        request.setContent(clearProfileRequest.toJson());
        setHeaders(request, SERVER_PROTOCOL_VERSION_V2);
        AbstractHttpResponse response = request.execute();
        return handler(request, response, ClearProfileResponse.class, null);
    }

    @Override
    public ActivationLockResponse activationLockDevices(ActivationLockRequest activationLockRequest) throws Exception {
        AbstractHttpRequest request = getRequest(ServiceURL.ACTIVATION_LOCK, HttpMethods.POST);
        request.setContent(activationLockRequest.toJson());
        setHeaders(request, SERVER_PROTOCOL_VERSION_V2);
        AbstractHttpResponse response = request.execute();
        return handler(request, response, ActivationLockResponse.class, null);
    }

    @Override
    public DeviceListResponse deviceDetails(DeviceListRequest deviceListRequest) throws Exception {
        AbstractHttpRequest request = getRequest(ServiceURL.DEVICES, HttpMethods.POST);
        request.setContent(deviceListRequest.toJson());
        request.addHeader(HEADER_SERVER_PROTOCOL_VERSION, SERVER_PROTOCOL_VERSION_V2);
        request.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);

        Function<String, AbstractHttpResponse> function = item -> {
            AbstractHttpResponse response = null;
            try {
                request.addHeader(HEADER_SESSION_AUTH_TOKEN, item);
                response = request.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        };

        return handler(request, function.apply(sessionToken()), DeviceListResponse.class, function);
    }

    @Override
    public FetchDeviceResponse listDevices(FetchDeviceRequest fetchDeviceRequest) throws Exception {
        AbstractHttpRequest request = getRequest(ServiceURL.SERVER_DEVICES, HttpMethods.POST);
        request.setContent(fetchDeviceRequest.toJson());
        request.addHeader(HEADER_SERVER_PROTOCOL_VERSION, SERVER_PROTOCOL_VERSION_V2);
        request.addHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE_OF_APP_JSON_UTF_8);

        Function<String, AbstractHttpResponse> function = item -> {
            AbstractHttpResponse response = null;
            try {
                request.addHeader(HEADER_SESSION_AUTH_TOKEN, item);
                response = request.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        };

        return handler(request, function.apply(sessionToken()), FetchDeviceResponse.class, function);
    }

    private AbstractHttpRequest getRequest(String url, String post) throws IOException {
        return transport.buildRequest(post, url);
    }

    @Override
    public FetchDeviceResponse syncListDevices(SyncDeviceRequest syncDeviceRequest) throws Exception {
        AbstractHttpRequest request = getRequest(ServiceURL.SERVER_SYNC, HttpMethods.POST);
        request.setContent(syncDeviceRequest.toJson());
        setHeaders(request, SERVER_PROTOCOL_VERSION_V2);
        AbstractHttpResponse response = request.execute();
        return handler(request, response, FetchDeviceResponse.class, null);
    }

    @Override
    public DeviceStatusResponse disownDevices(DeviceListRequest deviceListRequest) throws Exception {
        AbstractHttpRequest request = getRequest(ServiceURL.DEVICES_DISOWN, HttpMethods.POST);
        request.setContent(deviceListRequest.toJson());
        setHeaders(request, SERVER_PROTOCOL_VERSION_V2);
        AbstractHttpResponse response = request.execute();
        return handler(request, response, DeviceStatusResponse.class, null);
    }
}
