package com.appiron.appleservices.common.api;


import java.io.IOException;
import java.util.Map;

/**
 * Interface of HTTP request.
 *
 * <p>This allows providing a different implementation of the HTTP request that is more compatible
 * with the Java environment used.
 *
 * @author hpg
 */
public abstract class AbstractHttpRequest {

    public abstract Map<String, Object> headers();

    public abstract void setContent(String content) throws IOException;

    /**
     * Adds a header to the HTTP request.
     *
     * <p>Note that multiple headers of the same name need to be supported, in which case {@link
     * #addHeader} will be called for each instance of the header.
     *
     * @param name  header name
     * @param value header value
     */
    public abstract void addHeader(String name, String value) throws IOException;

    /**
     * Sets the connection and read timeouts.
     *
     * <p>Default implementation does nothing, but subclasses should normally override.
     *
     * @param connectTimeout timeout in milliseconds to establish a connection or {@code 0} for an
     *                       infinite timeout
     * @param readTimeout    Timeout in milliseconds to read data from an established connection or
     *                       {@code 0} for an infinite timeout
     * @throws IOException I/O exception
     */
    public void setTimeout(int connectTimeout, int readTimeout) throws IOException {
    }

    /**
     * Sets the write timeout for POST/PUT requests.
     *
     * <p>Default implementation does nothing, but subclasses should normally override.
     *
     * @param writeTimeout timeout in milliseconds to establish a connection or {@code 0} for an
     *                     infinite timeout
     * @throws IOException I/O exception
     */
    public void setWriteTimeout(int writeTimeout) throws IOException {
    }

    /**
     * Executes the request and returns a low-level HTTP response object.
     */
    public abstract AbstractHttpResponse execute() throws IOException;

    /**
     * Get the request url.
     */
    public String getUrl() throws IOException {
        return null;
    }
}
