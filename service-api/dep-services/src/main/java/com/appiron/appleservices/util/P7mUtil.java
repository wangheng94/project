package com.appiron.appleservices.util;

import cn.hutool.core.io.IoUtil;
import org.bouncycastle.cms.CMSEnvelopedDataParser;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSTypedStream;
import org.bouncycastle.cms.RecipientInformation;
import org.bouncycastle.cms.jcajce.JceKeyTransEnvelopedRecipient;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.Security;
import java.util.Base64;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * @author hpg
 */
public class P7mUtil {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private P7mUtil() {

    }

    public static String getMessageContent(byte[] p7m, byte[] key, String password) throws IOException, CMSException {
        KeyPair keyPair = initKeyPair(key, password.toCharArray());
        String p7mContent = parseP7mContent(p7m);
        return getString(keyPair, p7mContent);
    }

    public static String getMessageContent(InputStream p7m, InputStream key, String password) throws Exception {
        KeyPair keyPair = initKeyPair(key, password.toCharArray());
        String p7mContent = parseP7mContent(p7m);
        return getString(keyPair, p7mContent);
    }

    private static String getString(KeyPair keyPair, String p7mContent) throws IOException, CMSException {
        InputStream inputStream = getMessageInputStream(Base64.getDecoder().decode(p7mContent), keyPair.getPrivate());
        if (inputStream == null) {
            return null;
        }
        Pattern begin = Pattern.compile("-----BEGIN MESSAGE-----");
        Pattern end = Pattern.compile("-----END MESSAGE-----");
        boolean start = false;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            if (end.matcher(line).find()) {
                break;
            } else if (begin.matcher(line).find()) {
                start = true;
            } else if (start) {
                builder.append(line);
            }
        }
        return builder.toString();
    }

    public static String getMessage(InputStream p7m, InputStream key, String password) throws Exception {
        KeyPair keyPair = initKeyPair(key, password.toCharArray());
        String p7mContent = parseP7mContent(p7m);
        return getMessage(Base64.getDecoder().decode(p7mContent), keyPair.getPrivate());
    }

    public static String getMessage(byte[] message, PrivateKey privateKey) throws IOException, CMSException {
        InputStream messageInputStream = getMessageInputStream(message, privateKey);
        return messageInputStream == null ? null : IoUtil.read(messageInputStream, StandardCharsets.UTF_8);
    }

    public static InputStream getMessageInputStream(byte[] message, PrivateKey privateKey) throws IOException, CMSException {
        CMSEnvelopedDataParser cmsEnvelopedDataParser = new CMSEnvelopedDataParser(message);
        Collection<RecipientInformation> recInfos = cmsEnvelopedDataParser.getRecipientInfos().getRecipients();
        Iterator<RecipientInformation> recipientIterator = recInfos.iterator();
        if (recipientIterator.hasNext()) {
            RecipientInformation recipientInformation = (RecipientInformation) recipientIterator.next();
            JceKeyTransEnvelopedRecipient jceKeyTransEnvelopedRecipient = new JceKeyTransEnvelopedRecipient(privateKey);
            CMSTypedStream contentStream = recipientInformation.getContentStream(jceKeyTransEnvelopedRecipient);
            return contentStream.getContentStream();
        }
        return null;
    }

    public static String parseP7mContent(byte[] content) throws IOException {
        Pattern compile = Pattern.compile("Content-.*: .*");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IoUtil.toStream(content)));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            if (!compile.matcher(line).find()) {
                builder.append(line);
            }
        }
        return builder.toString().trim();
    }

    public static String parseP7mContent(InputStream inputStream) throws IOException {
        Pattern compile = Pattern.compile("Content-.*: .*");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            if (!compile.matcher(line).find()) {
                builder.append(line);
            }
        }
        return builder.toString().trim();
    }

    public static KeyPair initKeyPair(byte[] key, char[] password) throws IOException {
        return initKeyPair(IoUtil.toStream(key), password);
    }

    public static KeyPair initKeyPair(InputStream inputStream, char[] password) throws IOException {
        PEMParser pemParser = new PEMParser(new InputStreamReader(inputStream));
        Object object = pemParser.readObject();
        pemParser.close();
        PEMDecryptorProvider decProv = new JcePEMDecryptorProviderBuilder().build(password);
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider("BC");
        //获得密钥对
        KeyPair kp;
        if (object instanceof PEMEncryptedKeyPair) {
            kp = converter.getKeyPair(((PEMEncryptedKeyPair) object).decryptKeyPair(decProv));
        } else {
            kp = converter.getKeyPair((PEMKeyPair) object);
        }
        return kp;
    }

}
