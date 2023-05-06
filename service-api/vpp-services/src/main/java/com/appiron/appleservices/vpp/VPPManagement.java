package com.appiron.appleservices.vpp;


import com.appiron.appleservices.common.api.HttpTransport;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.IoUtil;
import com.appiron.appleservices.common.JSON;
import lombok.Getter;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author hpg
 */
public class VPPManagement {

    @Getter
    private final String sTokens;

    @Getter
    private final String token;

    @Getter
    private final HttpTransport transport;

    private final VPPManagementVersion2Api vppManagementVersion2Api;

    private VPPManagement(String sTokens, HttpTransport transport) {
        this.sTokens = sTokens;
        this.transport = transport;
        this.token = sTokens(sTokens).getToken();
        this.vppManagementVersion2Api = new VPPManagementVersion2Api(this, transport);
    }

    public VPPManagementVersion2Api api() {
        return (VPPManagementVersion2Api) this.vppManagementVersion2Api;
    }

    public STokens sTokens() {
        return sTokens(this.sTokens);
    }

    private STokens sTokens(String sTokens) {
        return JSON.parse(Base64.decodeStr(sTokens), STokens.class);
    }

    public static Builder create() {
        return new Builder();
    }

    public static class Builder {

        private HttpTransport transport;

        private String sTokens;

        private InputStream vppToken;

        public Builder() {
        }

        public Builder setSTokens(String sTokens) {
            this.sTokens = sTokens;
            return this;
        }

        public Builder setVPPToken(InputStream vppToken) {
            this.vppToken = vppToken;
            return this;
        }

        public Builder setTransport(HttpTransport transport) {
            this.transport = transport;
            return this;
        }

        public VPPManagement build() {
            if (vppToken != null && this.sTokens == null) {
                this.sTokens = IoUtil.read(vppToken, StandardCharsets.UTF_8);
                IoUtil.close(vppToken);
            }
            return new VPPManagement(this.sTokens, this.transport);
        }
    }
}
