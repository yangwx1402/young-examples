package com.young.java.examples.baiduindex.support;

import java.io.InputStream;
import java.util.Map;

/**
 * @author shazam
 * @DATE 2017/10/11
 */
public class HttpResult {
    //status code
    private int code;
    //网页文本
    private InputStream stream;
    //错误信息
    private String message;
    //url
    private String url;

    private Map<String,String> headers;

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

