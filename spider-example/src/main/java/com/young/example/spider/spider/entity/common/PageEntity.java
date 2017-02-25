package com.young.example.spider.spider.entity.common;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class PageEntity implements Serializable {
    private int totalPage;

    private int totalNum;

    private int page;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
