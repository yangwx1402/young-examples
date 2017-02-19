package com.young.spider.entity;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class PageEntity implements Serializable {
    private boolean hasMore;

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
