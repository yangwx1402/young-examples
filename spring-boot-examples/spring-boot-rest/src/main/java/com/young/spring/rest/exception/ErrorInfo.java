package com.young.spring.rest.exception;

/**
 * Created by yangyong3 on 2017/2/3.
 */
public class ErrorInfo {
    private String url;

    public ErrorInfo(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
