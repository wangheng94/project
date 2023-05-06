import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ArrayUtil;
import com.appiron.appleservices.common.api.AbstractHttpRequest;
import com.appiron.appleservices.common.api.AbstractHttpResponse;
import com.appiron.appleservices.common.api.HttpMethods;
import com.appiron.appleservices.common.api.HttpTransport;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.client.HttpRequest;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hpg
 * @date 2023/4/17 11:10
 */

public class VertxHttpTransport extends HttpTransport {

    private final WebClient client;

    public VertxHttpTransport() {
        Vertx vertx = Vertx.vertx();
        client = WebClient.create(vertx);
    }

    @Override
    public AbstractHttpRequest buildRequest(String method, String url) throws IOException {
        HttpRequest<Buffer> request = null;
        switch (method) {
            case HttpMethods.DELETE:
                request = client.deleteAbs(url);
                break;
            case HttpMethods.GET:
                request = client.getAbs(url);
                break;
            case HttpMethods.POST:
                request = client.postAbs(url);
                break;
            case HttpMethods.PUT:
                request = client.putAbs(url);
                break;
            default:
                break;
        }
        return new VertxRequest(request);
    }

    @Override
    public AbstractHttpRequest buildRequest(String method, Map<String, Object> params, String url) throws IOException {
        AbstractHttpRequest abstractHttpRequest = buildRequest(method, url);
        VertxRequest request = (VertxRequest) abstractHttpRequest;
        for (Map.Entry<String, Object> param : params.entrySet()) {
            String key = param.getKey();
            Object value = param.getValue();
            request.addParams(key, value.toString());
        }
        return request;
    }


    public static class VertxRequest extends AbstractHttpRequest {

        private final HttpRequest<Buffer> request;

        public VertxRequest(HttpRequest<Buffer> request) {
            this.request = request;
        }

        @Override
        public Map<String, Object> headers() {
            return new HashMap<String, Object>() {
                {
                    List<Entry<String, String>> entries = request.headers().entries();
                    for (Entry<String, String> entry : entries) {
                        this.put(entry.getKey(), entry.getValue());
                    }
                }
            };
        }

        @Override
        public void setContent(String content) throws IOException {
            request.sendBuffer(Buffer.buffer(content));
        }

        @Override
        public void addHeader(String name, String value) throws IOException {
            request.putHeader(name, value);
        }

        public void addParams(String name, String value) throws IOException {
            request.addQueryParam(name, value);
        }

        @Override
        public AbstractHttpResponse execute() throws IOException {
            Future<HttpResponse<Buffer>> responseFuture = request.send();
            while (!responseFuture.isComplete()) {

            }
            return new VertxResponse(responseFuture.result());
        }

        public HttpRequest<Buffer> getRequest() {
            return request;
        }
    }


    public static class VertxResponse extends AbstractHttpResponse {


        private final HttpResponse<Buffer> result;

        private final String bytes;

        public VertxResponse(HttpResponse<Buffer> result) {
            this.result = result;
            this.bytes = result.bodyAsString();
        }

        @Override
        public InputStream getContent() throws IOException {
            return IoUtil.toStream(bytes.getBytes(StandardCharsets.UTF_8));
        }

        @Override
        public String getContentEncoding() throws IOException {
            return null;
        }

        @Override
        public long getContentLength() throws IOException {
            return ArrayUtil.length(bytes);
        }

        @Override
        public String getContentType() throws IOException {
            return null;
        }

        @Override
        public String getStatusLine() throws IOException {
            return result.statusMessage();
        }

        @Override
        public int getStatusCode() throws IOException {
            return result.statusCode();
        }

        @Override
        public String getReasonPhrase() throws IOException {
            return null;
        }

        @Override
        public String getHeaderValue(String name) {
            return result.headers().get(name);
        }

        @Override
        public int getHeaderCount() throws IOException {
            return result.headers().entries().size();
        }

        @Override
        public String getHeaderName(int index) throws IOException {
            return result.headers().entries().get(index).getKey();
        }

        @Override
        public String getHeaderValue(int index) throws IOException {
            return result.headers().entries().get(index).getValue();
        }
    }

}
