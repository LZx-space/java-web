/*
 * Copyright (c) 2019, LZx
 */

package space.nature.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 同步处理的HTTP客户端
 */
public abstract class HttpSyncClient {

    private static final CloseableHttpClient CLIENT;

    private static final int MAX_CONNECTION_PER_ROUTE = 800;

    // 套接字发送和返回/修改数据的最大时间差
    private static final int SOCKET_TIMEOUT = 1;

    // 建立连接的超时时间
    private static final int CONNECT_TIMEOUT = 1;

    // 从连接池获取连接的超时时间
    private static final int CONNECTION_REQUEST_TIMEOUT = 1;

    static {
        CLIENT = HttpClients.custom()
                .setConnectionManager(connectionManager())
                .setDefaultRequestConfig(requestConfig())
                .evictIdleConnections(6, TimeUnit.SECONDS)
                .disableAuthCaching()
                .disableAutomaticRetries()
                .disableContentCompression()
                .disableCookieManagement()
                .disableRedirectHandling()
                .build();
    }

    private static HttpClientConnectionManager connectionManager() {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setDefaultConnectionConfig(ConnectionConfig.custom()
                .setMalformedInputAction(CodingErrorAction.IGNORE)
                .setUnmappableInputAction(CodingErrorAction.IGNORE)
                .setCharset(StandardCharsets.UTF_8).build());
        manager.setValidateAfterInactivity(2000);
        manager.setDefaultMaxPerRoute(MAX_CONNECTION_PER_ROUTE);
        manager.setMaxTotal(MAX_CONNECTION_PER_ROUTE);
        return manager;
    }

    private static RequestConfig requestConfig() {
        return RequestConfig.custom().setAuthenticationEnabled(false)
                .setCircularRedirectsAllowed(false)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT)
                /*
                 * 对于需要服务端验证的实体可以关闭的请求（POST，PUT...），开启此项将显著提高
                 * 性能，是否生效取决于服务端和请求头，如服务端不支持HTTP/1.1将出现异常
                 */
                .setExpectContinueEnabled(true).build();
    }

    /**
     * @param uri    请求地址
     * @param entity 请求实体
     * @return 结果字符串
     * @throws IOException
     */
    public static String post(String uri, HttpEntity entity) throws IOException {
        HttpPost post = new HttpPost(uri);
        post.setEntity(entity);
        return CLIENT.execute(post, new BasicResponseHandler());
    }

}
