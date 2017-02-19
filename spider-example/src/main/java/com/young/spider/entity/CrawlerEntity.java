package com.young.spider.entity;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class CrawlerEntity<T> implements Serializable{
    private T meta;

    private String body;

    public T getMeta() {
        return meta;
    }

    public void setMeta(T meta) {
        this.meta = meta;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
