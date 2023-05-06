package com.appiron.appleservices.common.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Abstract HTTP transport.
 *
 * @author hpg
 */
public abstract class HttpTransport {

    static final Logger LOGGER = Logger.getLogger(HttpTransport.class.getName());

    /**
     * All valid request methods as specified in {@link #supportsMethod(String)}, sorted in ascending
     * alphabetical order.
     */
    private static final String[] SUPPORTED_METHODS = {
            HttpMethods.DELETE, HttpMethods.GET, HttpMethods.POST, HttpMethods.PUT
    };

    static {
        Arrays.sort(SUPPORTED_METHODS);
    }

    /**
     * Returns whether a specified HTTP method is supported by this transport.
     *
     * <p>Default implementation returns true if and only if the request method is {@code "DELETE"},
     * {@code "GET"}, {@code "POST"}, or {@code "PUT"}. Subclasses should override.
     *
     * @param method HTTP method
     * @throws IOException I/O exception
     */
    public boolean supportsMethod(String method) throws IOException {
        return Arrays.binarySearch(SUPPORTED_METHODS, method) >= 0;
    }

    /**
     * Returns whether the transport is mTLS.
     *
     * @return boolean indicating if the transport is mTLS.
     */
    public boolean isMtls() {
        return false;
    }


    /**
     * Builds a low level HTTP request for the given HTTP method.
     *
     * @param method HTTP method
     * @param url    URL
     * @return new low level HTTP request
     * @throws IllegalArgumentException if HTTP method is not supported
     */
    public abstract AbstractHttpRequest buildRequest(String method, String url) throws IOException;


    /**
     * Builds a low level HTTP request for the given HTTP method.
     *
     * @param method HTTP method
     * @param url    URL
     * @return new low level HTTP request
     * @throws IllegalArgumentException if HTTP method is not supported
     */
    public abstract AbstractHttpRequest buildRequest(String method, Map<String, Object> params, String url) throws IOException;


    /**
     * Default implementation does nothing, but subclasses may override to possibly release allocated
     * system resources or close connections.
     *
     * @throws IOException I/O exception
     */
    public void shutdown() throws IOException {
    }
}
