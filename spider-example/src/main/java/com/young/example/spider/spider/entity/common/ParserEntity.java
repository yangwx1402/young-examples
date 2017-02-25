package com.young.example.spider.spider.entity.common;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class ParserEntity<DATA> implements Serializable {

    private DATA data;

    private int status;

    private PageEntity page;

    public DATA getData() {
        return data;
    }

    public void setData(DATA data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public PageEntity getPage() {
        return page;
    }

    public void setPage(PageEntity page) {
        this.page = page;
    }
}
