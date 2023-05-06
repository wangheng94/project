package com.appiron.appleservices.dep;


import com.appiron.appleservices.common.api.HttpTransport;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.io.IoUtil;
import com.appiron.appleservices.common.JSON;
import com.appiron.appleservices.util.P7mUtil;
import lombok.Getter;
import org.bouncycastle.cms.CMSException;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * @author hpg
 */
public class DEPManagement {

    @Getter
    private final byte[] key;

    @Getter
    private final byte[] cert;

    @Getter
    private final String password;

    @Getter
    private final String token;

    @Getter
    private final HttpTransport transport;

    @Getter
    private final IDEPManagementApi managementApi;

    private DEPManagement(InputStream ca, InputStream key, String password, String token, HttpTransport transport) throws IOException, CMSException {
        if (ca != null) {
            this.cert = IoUtil.readBytes(ca);
        } else {
            this.cert = null;
        }

        if (key != null) {
            this.key = IoUtil.readBytes(key);
            this.password = password;
        } else {
            this.key = null;
            this.password = null;
        }

        if (key == null && ca == null) {
            this.token = token;
        } else {
            this.token = P7mUtil.getMessageContent(this.cert, this.key, password);
        }

        this.transport = transport;

        this.managementApi = new DEPManagementApi(this, transport);
    }


    public String getOauthString() {
        return token;
    }

    public Oauth getOauth() {
        return JSON.parse(this.token, Oauth.class);
    }

    public boolean isExpire() {
        ZonedDateTime dateTime = ZonedDateTime.parse(getOauth().getAccessTokenExpiry());
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        Duration duration = LocalDateTimeUtil.between(LocalDateTimeUtil.now(), localDateTime);
        return duration.getSeconds() < 0;
    }

    public DEPManagementApi api() {
        return (DEPManagementApi) this.managementApi;
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {

        private InputStream ca;

        private InputStream key;

        private String password;

        private String token;

        private HttpTransport transport;

        public Builder() {
        }

        public Builder setCert(InputStream ca) {
            this.ca = ca;
            return this;
        }

        public Builder setKey(InputStream key, String password) {
            this.key = key;
            this.password = password;
            return this;
        }

        public Builder setTransport(HttpTransport transport) {
            this.transport = transport;
            return this;
        }

        public Builder setToken(String token) {
            this.token = token;
            return this;
        }

        public DEPManagement build() throws Exception {
            return new DEPManagement(this.ca, this.key, this.password, this.token, this.transport);
        }
    }
}
