package com.appiron.appleservices.common;

import com.appiron.appleservices.common.api.AbstractHttpRequest;
import com.appiron.appleservices.common.api.AbstractHttpResponse;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author hpg
 */
public interface ResponseHandler {

    <T> T handler(String url, AbstractHttpRequest request, AbstractHttpResponse response, Class<T> clazz) throws Exception;

    default <T> T handler(String url, AbstractHttpRequest request, AbstractHttpResponse response, Class<T> clazz, Function<String, AbstractHttpResponse> function) throws Exception {
        throw new UnsupportedOperationException();
    }
}
