package com.appiron.appleservices.dep;

import cn.hutool.core.io.IoUtil;
import com.appiron.appleservices.common.JSON;
import com.appiron.appleservices.common.ResponseHandler;
import com.appiron.appleservices.common.api.AbstractHttpRequest;
import com.appiron.appleservices.common.api.AbstractHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author hpg
 */
public class DefaultResponseHandler implements ResponseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDEPManagementApi.class);

    private final AbstractDEPManagementApi api;

    private static final Map<String, ErrorMessage> data = new HashMap<>();

    static {
        for (ErrorMessage value : ErrorMessage.values()) {
            data.put(value.getBody(), value);
        }
    }

    public DefaultResponseHandler(AbstractDEPManagementApi api) {
        this.api = api;
    }

    @Override
    public <T> T handler(String url, AbstractHttpRequest request, AbstractHttpResponse response, Class<T> clazz) throws Exception {
        return this.handler(url, request, response, clazz, null);
    }

    @Override
    public <T> T handler(String url, AbstractHttpRequest request, AbstractHttpResponse response, Class<T> clazz, Function<String, AbstractHttpResponse> function) throws Exception {
        String statusLine = response.getStatusLine();
        Objects.requireNonNull(statusLine, "statusLine must not be null.");
        int statusCode = response.getStatusCode();
        LOGGER.debug("Status:" + statusLine);
        // https://developer.apple.com/business/documentation/MDM-Protocol-Reference.pdf
        // The Device Enrollment Program service periodically issues a new X-ADM-Auth-Session in its response to a
        // service call; the MDM server can use this new header value for any subsequent calls.
        // All responses may return a new X-ADM-Auth-Session token, which the MDM server should use in subsequent requests.
        String sessionToken = response.getHeaderValue("X-ADM-Auth-Session");
        if (sessionToken != null) {
            api.updateSessionToken(sessionToken);
        }

        if (statusCode == 200) {
            String content = IoUtil.read(response.getContent(), StandardCharsets.UTF_8);
            api.reSet();
            return JSON.parse(content, clazz);
        } else {
            String msg = IoUtil.read(response.getContent(), StandardCharsets.UTF_8);
            LOGGER.debug("Body: " + msg);
            Error error = new Error.Builder().statusCode(statusCode).url(url).headers(request.headers()).requestBody(null).requestParams(null).tips(msg(msg)).responseBody(msg).build();
            if (statusCode == 400) {
                // An HTTP 400 error with MALFORMED_REQUEST_BODY in the response body indicates that the request body was not valid JSON.
                LOGGER.error("The request body was not valid JSON.");
                throw new DEPRequest400Exception(error);
            } else if (statusCode == 401) {
                // An HTTP 401 error with UNAUTHORIZED in the response body indicates that the authentication token has
                // expired. This error indicates that the MDM server should obtain a new auth token from the
                // https://mdmenrollment.apple.com/session endpoint.

                //Retry
                if (function != null) {
                    if (api.currentTimes() < 3) {
                        api.incrTimes();
                        return handler(url, request, function.apply(api.sessionToken()), clazz, function);
                    }
                    api.incrTimes();
                }
                LOGGER.error("The authentication token has expired.");
                throw new DEPRequest401Exception(error);
            } else if (statusCode == 403) {
                LOGGER.error("The authentication token may be invalid.");
                throw new DEPRequest403Exception(error);
            } else if (statusCode == 404) {
                LOGGER.error("The request parameter(body) may be invalid.");
                throw new DEPRequest404Exception(error);
            } else if (statusCode == 405) {
                LOGGER.error("The method (query type) is not valid.");
                throw new DEPRequest405Exception(error);
            } else if (statusCode >= 500) {
                LOGGER.error("The DEP server failed to provide service properly.");
                throw new DEPRequest50xException(error);
            }
            throw new DEPRequestException(error);
        }
    }

    private static String msg(String body) {
        ErrorMessage errorMessage = data.get(body);
        if (errorMessage == null) {
            return null;
        }
        String msg = errorMessage.getMsg();
        return body + ": " + msg;
    }

}
