package com.young.spider.http;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/15.
 */
@NotThreadSafe
public class HttpWalker {

    private String userAgent = "Mozilla/5.0 (X11; U; Linux i686; zh-CN; rv:1.9.1.2) Gecko/20090803 Fedora/3.5.2-2.fc11 Firefox/3.5.2";

    private int timeout = 10000;

    private int poolSize = 100;

    private static final String charset = "utf-8";

    private static final Logger logger = LogManager.getLogger(HttpWalker.class);

    private HttpClientBuilder httpClientBuilder;

    public HttpWalker(String userAgent, int timeout) {
        this(userAgent, timeout, 100);
    }

    public HttpWalker(String userAgent, int timeout, int poolSize) {
        this.userAgent = userAgent;
        this.timeout = timeout;
        this.poolSize = poolSize;
        RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
        httpClientBuilder = HttpClients.custom().setUserAgent(userAgent).setMaxConnTotal(poolSize)
                .setMaxConnPerRoute(poolSize).setDefaultRequestConfig(config);
    }

    public HttpWalker() {
        RequestConfig config = RequestConfig.custom().setSocketTimeout(timeout).setConnectTimeout(timeout).build();
        httpClientBuilder = HttpClients.custom().setUserAgent(userAgent).setMaxConnTotal(poolSize)
                .setMaxConnPerRoute(poolSize).setDefaultRequestConfig(config);
    }

    private HttpClient newHttpClient() {
        return httpClientBuilder.build();
    }

    private void checkRequest(Request request) throws IOException {
        if (request == null)
            throw new IOException("request is null");
        if (request.getUrl() == null || "".equals(request.getUrl().trim()))
            throw new IOException("request's url is null");
    }

    public Response get(Request request) throws IOException {
        checkRequest(request);
        String url = processUrl(request.getUrl(), request.getParameters());
        HttpGet get = new HttpGet(url);
        return sendRequest(get, request);
    }

    public Response post(Request request) throws IOException {
        checkRequest(request);
        HttpPost post = new HttpPost(request.getUrl());
        HttpEntity entity = createEntity(request.getParameters());
        post.setEntity(entity);
        return sendRequest(post, request);
    }

    public Response delete(Request request) throws IOException {
        checkRequest(request);
        String url = processUrl(request.getUrl(), request.getParameters());
        HttpDelete delete = new HttpDelete(url);
        return sendRequest(delete, request);
    }

    private Response sendRequest(HttpUriRequest uriRequest, Request request) throws IOException {
        if (request.getHeader() != null && !request.getHeader().isEmpty()) {
            for (Map.Entry<String, String> entry : request.getHeader().entrySet()) {
                uriRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }
        HttpResponse response = newHttpClient().execute(uriRequest);
        return new Response(response.getStatusLine().getStatusCode(), IOUtils.toString(response.getEntity().getContent(),charset), response.getStatusLine().getReasonPhrase());
    }

    private HttpEntity createEntity(Map<String, String> params)
            throws UnsupportedEncodingException {
        if (params == null) {
            return null;
        }
        List<NameValuePair> args = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            args.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        HttpEntity entity = new UrlEncodedFormEntity(args, charset);
        return entity;
    }

    private String processUrl(String url, Map<String, String> params) {
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                url += entry.getKey() + "=" + entry.getValue();
            }
        }
        return url;
    }
}
