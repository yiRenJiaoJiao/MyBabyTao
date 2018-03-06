package com.babyTao.app.utils;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Administrator on 2018/1/26.
 */
public class HttpClientFactory {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientFactory.class);
    private static final String[] supportedProtocols = new String[]{"TLSv1"};
    private static PropertiesUtil propertiesUtil = null;

    public HttpClientFactory() {
    }

    public static CloseableHttpClient createHttpClient() {
        return createHttpClient(100, 10, 5000, 2);
    }

    public static CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute, int timeout, int retryExecutionCount) {
        try {
            logger.info("createHttpClient[maxTotal:{},maxPerRoute:{},timeout:{},retryExecutionCount:{}]", new Object[]{Integer.valueOf(maxTotal), Integer.valueOf(maxPerRoute), Integer.valueOf(timeout), Integer.valueOf(retryExecutionCount)});
            SSLContext e = SSLContexts.custom().useSSL().build();
            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(e, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
            poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
            SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(timeout).build();
            poolingHttpClientConnectionManager.setDefaultSocketConfig(socketConfig);
            return HttpClientBuilder.create().setConnectionManager(poolingHttpClientConnectionManager).setSSLSocketFactory(sf).setRetryHandler(new HttpClientFactory.HttpRequestRetryHandlerImpl(retryExecutionCount)).setProxy(getHttpHost()).build();
        } catch (KeyManagementException var8) {
            logger.info("createHttpClient", var8);
            var8.printStackTrace();
        } catch (NoSuchAlgorithmException var9) {
            logger.info("createHttpClient", var9);
            var9.printStackTrace();
        } catch (Exception var10) {
            logger.info("createHttpClient", var10);
            var10.printStackTrace();
        }

        return null;
    }

    public static CloseableHttpClient createKeyMaterialHttpClient(KeyStore keystore, String keyPassword, int timeout, int retryExecutionCount) {
        return createKeyMaterialHttpClient(keystore, keyPassword, supportedProtocols, timeout, retryExecutionCount);
    }

    public static CloseableHttpClient createKeyMaterialHttpClient(KeyStore keystore, String keyPassword, String[] supportedProtocols, int timeout, int retryExecutionCount) {
        try {
            SSLContext e = SSLContexts.custom().useSSL().loadKeyMaterial(keystore, keyPassword.toCharArray()).build();
            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(e, supportedProtocols, (String[])null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(timeout).build();
            return HttpClientBuilder.create().setDefaultSocketConfig(socketConfig).setSSLSocketFactory(sf).setRetryHandler(new HttpClientFactory.HttpRequestRetryHandlerImpl(retryExecutionCount)).build();
        } catch (KeyManagementException var8) {
            var8.printStackTrace();
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
        } catch (UnrecoverableKeyException var10) {
            var10.printStackTrace();
        } catch (KeyStoreException var11) {
            var11.printStackTrace();
        }

        return null;
    }

    private static HttpHost getHttpHost() throws IOException {
        HttpHost proxy = null;

        try {
            String e = propertiesUtil.readValue("wechatProxyIp");
            int wechatProxyPort = Integer.parseInt(propertiesUtil.readValue("wechatProxyPort"));
            String wechatProxyScheme = propertiesUtil.readValue("wechatProxyScheme");
            proxy = new HttpHost(e, wechatProxyPort, wechatProxyScheme);
        } catch (IOException var4) {
            logger.info("getHttpHost(),获得微信代理配置文件异常", var4);
        }
        return proxy;
    }

    static {
        try {
            propertiesUtil = new PropertiesUtil();
        } catch (IOException var1) {
            var1.printStackTrace();
        }

    }

    private static class HttpRequestRetryHandlerImpl implements HttpRequestRetryHandler {
        private int retryExecutionCount;

        public HttpRequestRetryHandlerImpl(int retryExecutionCount) {
            this.retryExecutionCount = retryExecutionCount;
        }

        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            if(executionCount > this.retryExecutionCount) {
                return false;
            } else if(exception instanceof InterruptedIOException) {
                return false;
            } else if(exception instanceof UnknownHostException) {
                return false;
            } else if(exception instanceof ConnectTimeoutException) {
                return true;
            } else if(exception instanceof SSLException) {
                return false;
            } else {
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
                return idempotent;
            }
        }
    }
}
