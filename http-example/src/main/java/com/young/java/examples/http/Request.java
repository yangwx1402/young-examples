package com.young.java.examples.http;

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

    public Request(String url, Map<String, String> header, Map<String, String> parameters,String encode) {
        this.url = url;
        this.header = header;
        this.parameters = parameters;
        this.encode = encode;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    private String url;

    private Map<String, String> header;

    private Map<String, String> parameters;

    private String encode;

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


    public String toString(){
        return "url="+url+",header = "+header;
    }

}
