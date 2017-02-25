package com.young.example.spider.http;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class Response implements Serializable{
    private Integer statusCode;

    private String content;

    private String message;

    public Response(Integer statusCode, String content, String message) {
        this.statusCode = statusCode;
        this.content = content;
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString(){
        return "statusCode = ["+statusCode+"], message is ["+message+"]";
    }
}
