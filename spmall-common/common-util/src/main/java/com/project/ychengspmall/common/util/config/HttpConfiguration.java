package com.project.ychengspmall.common.util.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConfiguration {

    @Value("${http-client.max-total}")
    private Integer maxTotal;

    @Value("${http-client.default-max-per-route}")
    private Integer defaultMaxPerRoute;

    @Value("${http-client.connect-timeout}")
    private Integer connectTimeout;

    @Value("${http-client.connection-request}")
    private Integer connectionRequestTimeout;

    @Value("${http-client.socket-timeout}")
    private Integer socketTimeout;

    @Value("${http-client.validate-after-inactivity}")
    private Integer validateAfterInactivity;

    @Bean
    @ConditionalOnMissingBean(CloseableHttpClient.class)
    public CloseableHttpClient closeableHttpClient() {
        try (PoolingHttpClientConnectionManager httpClientConnectionManager =
                     new PoolingHttpClientConnectionManager()) {
            // 最大连接数
            httpClientConnectionManager.setMaxTotal(maxTotal);
            // 设置到某个路由的最大连接数
            httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
            // 空闲永久连接检查间隔，官方推荐使用这个来检查永久链接的可用性，而不推荐每次请求的时候才去检查
            httpClientConnectionManager.setValidateAfterInactivity(validateAfterInactivity);
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
            httpClientBuilder.setConnectionManager(httpClientConnectionManager);
            return httpClientBuilder.build();
        }
    }

    @Bean
    @ConditionalOnMissingBean(RequestConfig.class)
    public RequestConfig requestConfig() {
        RequestConfig.Builder builder = RequestConfig.custom();
        // 连接超时时间（单位毫秒）
        builder.setConnectTimeout(connectTimeout)
                // 从连接池中获取到连接的最长时间（单位毫秒）
                .setConnectionRequestTimeout(connectionRequestTimeout)
                // 数据传输的最长时间（单位毫秒）
                .setSocketTimeout(socketTimeout);
        return builder.build();
    }

}
