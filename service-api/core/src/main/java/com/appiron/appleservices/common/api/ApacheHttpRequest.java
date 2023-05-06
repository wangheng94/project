/*
 * Copyright 2019 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.appiron.appleservices.common.api;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hpg
 */
final class ApacheHttpRequest extends AbstractHttpRequest {

    private final HttpClient httpClient;

    private final HttpRequestBase request;

    private final RequestConfig.Builder requestConfig;

    @SuppressWarnings("deprecation")
    ApacheHttpRequest(HttpClient httpClient, HttpRequestBase request) {
        this.httpClient = httpClient;
        this.request = request;
        // disable redirects as google-http-client handles redirects
        this.requestConfig =
                RequestConfig.custom()
                        .setRedirectsEnabled(false)
                        .setNormalizeUri(false)
                        // TODO(chingor): configure in HttpClientBuilder when available
                        .setStaleConnectionCheckEnabled(false);
    }

    @Override
    public Map<String, Object> headers() {
        HashMap<String, Object> data = new HashMap<>();
        for (Header header : request.getAllHeaders()) {
            data.put(header.getName(), header.getValue());
        }
        return data;
    }

    @Override
    public void setContent(String content) throws IOException {
        if (request instanceof HttpEntityEnclosingRequestBase) {
            ((HttpEntityEnclosingRequestBase) request).setEntity(new StringEntity(content));
        }
    }

    @Override
    public void addHeader(String name, String value) {
        request.addHeader(name, value);
    }

    @Override
    public void setTimeout(int connectTimeout, int readTimeout) throws IOException {
        requestConfig.setConnectTimeout(connectTimeout).setSocketTimeout(readTimeout);
    }

    @Override
    public AbstractHttpResponse execute() throws IOException {
        request.setConfig(requestConfig.build());
        return new ApacheHttpResponse(request, httpClient.execute(request));
    }

    @Override
    public String getUrl() throws IOException {
        return request.getURI().toURL().toString();
    }
}
