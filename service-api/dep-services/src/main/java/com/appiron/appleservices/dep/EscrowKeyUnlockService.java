package com.appiron.appleservices.dep;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import org.apache.http.HttpHeaders;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

/**
 * @author hpg
 */
public class EscrowKeyUnlockService implements IEscrowKeyUnlockService {

    private CloseableHttpClient httpClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDEPManagementApi.class);

    public EscrowKeyUnlockService(InputStream cert, String secret) {
        init(cert, secret);
    }

    private void init(InputStream cert, String secret) {
        KeyStore keyStore;
        try {
            keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(cert, secret.toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        SSLContext sslContext;
        try {
            sslContext = SSLContexts.custom().setProtocol("TLSv1.2").loadKeyMaterial(keyStore, secret.toCharArray())
                    .loadTrustMaterial(new TrustAllStrategy()).build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException | UnrecoverableKeyException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        this.httpClient = HttpClients.custom()
                .setDefaultRequestConfig(RequestConfig.DEFAULT)
                .setConnectionTimeToLive(10, TimeUnit.MINUTES)
                .setMaxConnTotal(3)
                .setSSLContext(sslContext).build();
    }

    @Override
    public EscrowKeyUnlockStatusResponse escrowKeyUnlock(String code, String escrowKey, String orgName,
                                                         String guid, String serialNumber, String productType,
                                                         String imei1, String imei2, String meid) throws IOException {
        RequestBuilder builder = RequestBuilder.post(ServiceURL.ESCROW_KEY_UNLOCK);
        builder.addParameter("serial", serialNumber)
                .addParameter("productType", productType)
                .addParameter("meid", meid)
                .addParameter("imei", imei1);
        if (StrUtil.isNotEmpty(imei2)) {
            builder.addParameter("imei2", imei2);
        }
        builder.addHeader(HttpHeaders.CONTENT_TYPE, "application/x-www-form-urlencoded");
        // "escrowKey=49GDJ-DZX3C-LPM2-2UYN-KYW6-PP47&orgName=AppIron111&guid=1233455";
        String body = String.format("escrowKey=%s&orgName=%s&guid=%s", code, orgName, guid);
        builder.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
        HttpUriRequest request = builder.build();
        CloseableHttpResponse response = httpClient.execute(request);
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode == 200) {
            LOGGER.info("Activation succeeded. SerialNumber:{}", serialNumber);
            InputStream stream = response.getEntity().getContent();
            String content = IoUtil.read(stream, StandardCharsets.UTF_8);
            return new EscrowKeyUnlockStatusResponse(content, statusCode);
        } else {
            LOGGER.info("Activation failed. SerialNumber:{}", serialNumber);
            String content = IoUtil.read(response.getEntity().getContent(), StandardCharsets.UTF_8);
            if (statusCode == 400) {
                LOGGER.error("{}. Error:\n{}", "Failure: bad request; likely cause is a malformed request query or body.", content);
            } else if (statusCode == 404) {
                LOGGER.error("{}. Error:\n{}", "Failure: device is not found, or escrowKey is invalid.", content);
            } else if (statusCode == 500) {
                LOGGER.error("{}. Error:\n{}", "Unexpected server error; try again later.", content);
            }
            return new EscrowKeyUnlockStatusResponse(content, statusCode);
        }
    }

    public static class Builder {

        private InputStream certInputStream;

        private String password;

        public Builder cert(InputStream certInputStream) {
            this.certInputStream = certInputStream;
            return this;
        }

        public Builder cert(byte[] cert) {
            this.certInputStream = IoUtil.toStream(cert);
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public EscrowKeyUnlockService build() {
            return new EscrowKeyUnlockService(this.certInputStream, this.password);
        }
    }
}
