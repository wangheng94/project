package com.appiron.appleservices.common.api;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;

import java.io.IOException;
import java.net.ProxySelector;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * HTTP transport based on the Apache HTTP Client library.
 *
 * @author hpg
 */
public final class ApacheHttpTransport extends HttpTransport {

    /**
     * Apache HTTP client.
     */
    private final HttpClient httpClient;

    /**
     * If the HTTP client uses mTLS channel.
     */
    private final boolean isMtls;

    /**
     * Constructor that uses {@link #newDefaultHttpClient()} for the Apache HTTP client.
     *
     * @since 1.30
     */
    public ApacheHttpTransport() {
        this(newDefaultHttpClient(), false);
    }

    /**
     * Constructor that allows an alternative Apache HTTP client to be used.
     *
     * @param httpClient Apache HTTP client to use
     */
    public ApacheHttpTransport(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.isMtls = false;
    }

    /**
     * Constructor that allows an alternative Apache HTTP client to be used.
     *
     * @param httpClient Apache HTTP client to use
     * @param isMtls     If the HTTP client is mutual TLS
     * @since 1.38
     */
    public ApacheHttpTransport(HttpClient httpClient, boolean isMtls) {
        this.httpClient = httpClient;
        this.isMtls = isMtls;
    }

    /**
     * Creates a new instance of the Apache HTTP client that is used by the {@link
     * #ApacheHttpTransport()} constructor.
     *
     * @return new instance of the Apache HTTP client
     */
    public static HttpClient newDefaultHttpClient() {
        return newDefaultHttpClientBuilder().build();
    }

    /**
     * Creates a new Apache HTTP client builder that is used by the {@link #ApacheHttpTransport()}
     * constructor.
     *
     * @return new instance of the Apache HTTP client
     */
    public static HttpClientBuilder newDefaultHttpClientBuilder() {
        return HttpClientBuilder.create()
                .useSystemProperties()
                .setSSLSocketFactory(SSLConnectionSocketFactory.getSocketFactory())
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(20)
                .setConnectionTimeToLive(-1, TimeUnit.MILLISECONDS)
                .setRoutePlanner(new SystemDefaultRoutePlanner(ProxySelector.getDefault()))
                .disableRedirectHandling()
                .disableAutomaticRetries();
    }

    @Override
    public boolean supportsMethod(String method) {
        return true;
    }

    @Override
    public ApacheHttpRequest buildRequest(String method, String url) {
        HttpRequestBase requestBase;
        switch (method) {
            case HttpMethods.DELETE:
                requestBase = new HttpDelete(url);
                break;
            case HttpMethods.GET:
                requestBase = new HttpGet(url);
                break;
            case HttpMethods.HEAD:
                requestBase = new HttpHead(url);
                break;
            case HttpMethods.PATCH:
                requestBase = new HttpPatch(url);
                break;
            case HttpMethods.POST:
                requestBase = new HttpPost(url);
                break;
            case HttpMethods.PUT:
                requestBase = new HttpPut(url);
                break;
            case HttpMethods.TRACE:
                requestBase = new HttpTrace(url);
                break;
            case HttpMethods.OPTIONS:
                requestBase = new HttpOptions(url);
                break;
            default:
                requestBase = new HttpExtensionMethod(method, url);
                break;
        }
        return new ApacheHttpRequest(httpClient, requestBase);
    }

    @Override
    public AbstractHttpRequest buildRequest(String method, Map<String, Object> params, String url) throws IOException {
        RequestBuilder builder = RequestBuilder.create(method).setUri(url);
        for (Map.Entry<String, Object> param : params.entrySet()) {
            String key = param.getKey();
            Object value = param.getValue();
            builder.addParameter(key, value.toString());
        }
        HttpUriRequest build = builder.build();
        return new ApacheHttpRequest(httpClient, (HttpRequestBase) build);
    }

    /**
     * Shuts down the connection manager and releases allocated resources. This closes all
     * connections, whether they are currently used or not.
     */
    @Override
    public void shutdown() throws IOException {
        if (httpClient instanceof CloseableHttpClient) {
            ((CloseableHttpClient) httpClient).close();
        }
    }

    /**
     * Returns the Apache HTTP client.
     */
    public HttpClient getHttpClient() {
        return httpClient;
    }

    /**
     * Returns if the underlying HTTP client is mTLS.
     */
    @Override
    public boolean isMtls() {
        return isMtls;
    }
}
