package com.appiron.appleservices.vpp;

import com.appiron.appleservices.common.api.AbstractHttpRequest;
import com.appiron.appleservices.common.api.AbstractHttpResponse;
import cn.hutool.core.io.IoUtil;
import com.appiron.appleservices.common.JSON;
import com.appiron.appleservices.common.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author hpg
 */
public class DefaultResponseHandler implements ResponseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultResponseHandler.class);

    @Override
    public <T> T handler(String url, AbstractHttpRequest request, AbstractHttpResponse response, Class<T> clazz) throws IOException {
        String statusLine = response.getStatusLine();
        int statusCode = response.getStatusCode();
        LOGGER.debug("Status:" + statusLine);
        String content = IoUtil.read(response.getContent(), StandardCharsets.UTF_8);
        if (statusCode == 200) {
            return JSON.parse(content, clazz);
        } else {
            if (statusCode == 400) {
                LOGGER.error("Status: " + statusLine + "," + "Bad Request.");
            } else if (statusCode == 401) {
                LOGGER.error("Status: " + statusLine + "," + "Unauthorized.The provided token is invalid. It may either be missing or expired.");
            } else if (statusCode == 500) {
                LOGGER.error("Status: " + statusLine + "," + "Internal Server Error.An internal server error occurred. Try again later.");
            } else {
                LOGGER.error("Status: " + statusLine);
            }
            throw new VPPRequestException(content);
        }
    }

}
