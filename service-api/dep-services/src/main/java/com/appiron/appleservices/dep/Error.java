package com.appiron.appleservices.dep;

import com.appiron.appleservices.common.JSON;
import lombok.Data;

import java.util.Map;

/**
 * @author hpg
 * 
 */
@Data
public class Error {

    private Error() {
    }

    private Map<String, Object> headers;

    private Map<String, Object> requestParams;

    private String requestBody;

    private String responseBody;

    private String url;

    private String tips;

    private int statusCode;

    private Error(Map<String, Object> headers, Map<String, Object> requestParams, String requestBody, String responseBody, String url, int statusCode, String tips) {
        this.headers = headers;
        this.requestParams = requestParams;
        this.requestBody = requestBody;
        this.responseBody = responseBody;
        this.url = url;
        this.tips = tips;
        this.statusCode = statusCode;
    }

    public static class Builder {

        private Map<String, Object> headers;

        private Map<String, Object> requestParams;

        private String tips;

        private String requestBody;

        private String responseBody;

        private String url;

        private int statusCode;

        public Builder headers(Map<String, Object> headers) {
            this.headers = headers;
            return this;
        }

        public Builder requestParams(Map<String, Object> requestParams) {
            this.requestParams = requestParams;
            return this;
        }

        public Builder requestBody(String json) {
            this.requestBody = json;
            return this;
        }

        public Builder responseBody(String json) {
            this.responseBody = json;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder tips(String tips) {
            this.tips = tips;
            return this;
        }

        public Error build() {
            return new Error(this.headers, this.requestParams, this.requestBody, this.responseBody, this.url, this.statusCode, this.tips);
        }
    }

    @Override
    public String toString() {
        return JSON.writeAsPrettyJsonString(this);
    }

}
