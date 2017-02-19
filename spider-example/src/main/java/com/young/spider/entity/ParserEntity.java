package com.young.spider.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class ParserEntity implements Serializable {

    private Map<String, Object> data;

    private int status;

    private PageEntity page;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
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
