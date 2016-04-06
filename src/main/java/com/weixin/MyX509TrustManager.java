package com.weixin;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * Created by Thomas on 2016/2/1.
 * 证书信任管理器（用于https请求）
 */
public class MyX509TrustManager implements X509TrustManager{
    public void checkClientTrusted(X509Certificate[] x509Certificates, String authType) throws CertificateException {

    }

    public void checkServerTrusted(X509Certificate[] x509Certificates, String authType) throws CertificateException {

    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
