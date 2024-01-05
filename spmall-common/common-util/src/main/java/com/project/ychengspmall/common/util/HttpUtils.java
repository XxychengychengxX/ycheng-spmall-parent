package com.project.ychengspmall.common.util;

import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@ConditionalOnMissingBean(HttpUtils.class)
public class HttpUtils {
    @Resource
    CloseableHttpClient httpClient;

    @Resource
    RequestConfig config;

    private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContextBuilder.create().setProtocol("TSL-8").build();
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String str) {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] xcs, String str) {

                }
            };
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
        } catch (KeyManagementException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StrUtil.isBlank(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StrUtil.isBlank(query.getKey()) && !StrUtil.isBlank(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StrUtil.isBlank(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StrUtil.isBlank(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }

    /**
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param bodys
     * @return
     * @throws Exception
     */
    public HttpResponse doPost(String host, String path, String method,
                               Map<String, String> headers,
                               Map<String, String> querys,
                               Map<String, String> bodys)
            throws Exception {


        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }

        return httpClient.execute(request);
    }

    /**
     * Post String
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public HttpResponse doPost(String host, String path, String method,
                               Map<String, String> headers,
                               Map<String, String> querys,
                               String body)
            throws Exception {


        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (StrUtil.isNotBlank(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }

    /**
     * get
     *
     * @param host 预请求的主机地址
     * @param path 预请求的路径
     * @param method 请求方法
     * @param headers 情趣头
     * @param querys 请求参数（Url后）
     * @return
     * @throws Exception
     */
    public HttpResponse doGet(String host, String path, String method,
                              Map<String, String> headers,
                              Map<String, String> querys)
            throws Exception {

        HttpGet request = new HttpGet(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        return httpClient.execute(request);
    }

    /**
     * 发送get请求
     *
     * @param url     请求URL
     * @param param   请求参数
     * @param headers 请求头信息
     * @return
     */
    public String doGet(String url, Map<String, String> param, Map<String, String> headers) {
        CloseableHttpResponse response = null;
        // 创建Http Get请求
        HttpGet httpGet = new HttpGet();
        httpGet.setConfig(config);
        // 添加请求头
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(httpGet::addHeader);
        }
        try {
            // 添加参数到URL中
            if (param != null) {
                StringBuilder sb = new StringBuilder(url).append("?");
                int count = 1;
                for (Map.Entry<String, String> entry : param.entrySet()) {
                    if (count == 1) {
                        sb.append(entry.getKey()).append('=').append(entry.getValue());
                    } else {
                        sb.append('&').append(entry.getKey()).append('=').append(entry.getValue());
                    }
                    count++;
                }
                httpGet.setURI(URI.create(sb.toString()));
            }
            // 执行http请求
            response = httpClient.execute(httpGet);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            log.error("调用第三方错误:{}", e.getMessage());
            return null;
        } finally {
            try {
                httpGet.abort();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
    }

    /**
     * 发送post请求
     *
     * @param url     请求URL
     * @param param   请求体
     * @param headers 请求头信息
     * @return
     */
    public String doPost(String url, Map<String, Object> param, Map<String, String> headers) {
        // 创建Http Post请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(config);
        //封装请求头
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach(httpPost::addHeader);
        }
        CloseableHttpResponse response = null;
        try {
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                //封装url参数
                for (Map.Entry<String, Object> entry : param.entrySet()) {
                    paramList.add(
                            new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("调用第三方错误:{}", e.getMessage());
            return null;
        } finally {
            try {
                httpPost.abort();
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
    }

    /**
     * Post stream
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public HttpResponse doPost(String host, String path, String method,
                               Map<String, String> headers,
                               Map<String, String> querys,
                               byte[] body)
            throws Exception {

        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }


    /**
     * Put stream
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public HttpResponse doPut(String host, String path, String method,
                              Map<String, String> headers,
                              Map<String, String> querys,
                              byte[] body)
            throws Exception {

        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }


    /**
     * Delete
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @return
     * @throws Exception
     */
    public HttpResponse doDelete(String host, String path, String method,
                                 Map<String, String> headers,
                                 Map<String, String> querys)
            throws Exception {

        HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        return httpClient.execute(request);
    }
}
