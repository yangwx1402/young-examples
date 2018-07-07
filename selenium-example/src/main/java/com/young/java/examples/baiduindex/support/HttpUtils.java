package com.young.java.examples.baiduindex.support;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shazam
 * @DATE 2017/10/11
 */
public class HttpUtils {
    private static Logger log = LoggerFactory.getLogger(HttpUtils.class);

    private String sendCharset = "utf-8";

    private int timeout = 6000;

    private static final String user_agent
        = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) "
        + "Chrome/61.0.3163.100 Safari/537.36";

    public enum HttpMethod {
        GET,
        PUT,
        DELETE,
        POST
    }

    public HttpUtils() {

    }

    public HttpUtils(String sendCharset, int timeout) {
        this.sendCharset = sendCharset;
        this.timeout = timeout;
    }

    public HttpResult sendRequest(String uri, HttpMethod method,
                                  Map<String, String> header, Map<String, String> params)
        throws UnsupportedOperationException,
        IOException {
        long start = System.currentTimeMillis();
        HttpUriRequest request = createRequest(uri, method, params);
        log.info("send request url=[" + uri + "],method=[" + method + "],params=[" + params + "]");
        if (header != null && header.size() > 0) {
            header.entrySet().forEach(entry -> request.addHeader(entry.getKey(), entry.getValue()));
        }
        HttpClient client = HttpClients.custom().setUserAgent(user_agent).build();
        HttpResponse response = client.execute(request);

        log.info("receive response cost time [" + (System.currentTimeMillis() - start) + "]");
        HttpResult weixinResponse = new HttpResult();
        Header[] headers = response.getAllHeaders();
        if(headers!=null && headers.length>0){
            Map<String,String> responseHeader = new HashMap<>();
            for(Header header1:headers){
                responseHeader.put(header1.getName(),header1.getValue());
            }
            weixinResponse.setHeaders(responseHeader);

        }
        weixinResponse.setStream(response.getEntity().getContent());
        weixinResponse.setMessage(response.getStatusLine().getReasonPhrase());
        weixinResponse.setCode(response.getStatusLine().getStatusCode());
        log.info("response =" + weixinResponse);
        return weixinResponse;
    }

    public HttpResult sendPostRequest(String uri, HttpMethod method, Map<String, String> header, String content)
        throws IOException {
        long start = System.currentTimeMillis();
        HttpUriRequest request = createPostRequest(uri, method, content);
        log.info("send request url=[" + uri + "],method=[" + method + "],content=[" + content + "]");
        if (header != null && header.size() > 0) {
            header.entrySet().forEach(entry -> request.addHeader(entry.getKey(), entry.getValue()));
        }
        HttpClient client = HttpClients.custom().setUserAgent(user_agent).build();
        HttpResponse response = client.execute(request);
        log.info("receive response cost time [" + (System.currentTimeMillis() - start) + "]");
        HttpResult weixinResponse = new HttpResult();
        weixinResponse.setStream(response.getEntity().getContent());
        weixinResponse.setMessage(response.getStatusLine().getReasonPhrase());
        weixinResponse.setCode(response.getStatusLine().getStatusCode());
        log.info("response =" + shorten(JSON.toJSONString(weixinResponse), 500));
        return weixinResponse;
    }

    public static String shorten(String source, int size) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        if (source.length() <= size) {
            return source;
        }
        try {
            int endIndex = Math.max(size, source.length() - 200);
            return source.substring(0, size) + "......" + source.substring(endIndex, source.length());
        } catch (Exception ex) {
            return source;
        }
    }

    public HttpUriRequest createPostRequest(String uri,HttpMethod method, String content) {
        HttpEntity entity = null;
        HttpPost post = null;
        if (HttpMethod.POST == method) {
            post = new HttpPost(uri);
            RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout).setConnectTimeout(timeout)
                .build();
            post.setConfig(requestConfig);

            entity = createStringEntity(content);
            if (entity != null) { post.setEntity(entity); }

        }
        return post;
    }

    private HttpEntity createStringEntity(String content) {
        return new StringEntity(content, "utf-8");
    }

    private HttpUriRequest createRequest(String uri, HttpMethod method, Map<String, String> params)
        throws UnsupportedEncodingException {
        HttpEntity entity = null;
        if (HttpMethod.GET == method) {
            if (params != null && params.size() > 0) {
                uri += "?";
                for (Map.Entry<String, String> kv : params.entrySet()) {
                    uri += kv.getKey() + "=" + kv.getValue() + "&";
                }
            }
            HttpGet get = new HttpGet(uri);
            RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout).setConnectTimeout(timeout)
                .build();
            get.setConfig(requestConfig);

            return get;
        } else if (HttpMethod.PUT == method) {
            HttpPut put = new HttpPut(uri);
            RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout).setConnectTimeout(timeout)
                .build();
            put.setConfig(requestConfig);
            entity = createEntity(params);
            if (entity != null) {
                put.setEntity(entity);
            }
            return put;
        } else if (HttpMethod.DELETE == method) {
            HttpDelete delete = new HttpDelete(uri);
            RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout).setConnectTimeout(timeout)
                .build();
            delete.setConfig(requestConfig);
            return delete;
        } else if (HttpMethod.POST == method) {
            HttpPost post = new HttpPost(uri);
            RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout).setConnectTimeout(timeout)
                .build();
            post.setConfig(requestConfig);
            entity = createEntity(params);
            if (entity != null) {
                post.setEntity(entity);
            }
            return post;
        } else {
            HttpGet get = new HttpGet(uri);
            RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(timeout).setConnectTimeout(timeout)
                .build();
            get.setConfig(requestConfig);
            return get;
        }

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
        HttpEntity entity = new UrlEncodedFormEntity(args, sendCharset);
        return entity;
    }

}
