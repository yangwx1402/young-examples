package com.young.spider.http;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class Request implements Serializable {
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Request(String url) {
        this.url = url;
    }


    public Request(String url, Map<String, String> header) {
        this.url = url;
        this.header = header;
    }

    public Request(String url, Map<String, String> header, Map<String, String> parameters) {
        this.url = url;
        this.header = header;
        this.parameters = parameters;
    }

    public Request(String url, Map<String, String> header, Map<String, String> parameters, byte[] content) {
        this.url = url;
        this.header = header;
        this.parameters = parameters;
        this.content = content;
    }

    private String url;

    private Map<String, String> header;

    private Map<String, String> parameters;

    private byte[] content;

    public Map<String, String> getHeader() {
        return header;
    }

    public void setHeader(Map<String, String> header) {
        this.header = header;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }


}
