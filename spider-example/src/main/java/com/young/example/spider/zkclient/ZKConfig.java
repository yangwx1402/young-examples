package com.young.example.spider.zkclient;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class ZKConfig {
    private String zkServerStr;

    private Integer retry_interval;

    private Integer max_retries;

    public ZKConfig(String zkServerStr, Integer retry_interval, Integer max_retries) {
        this.zkServerStr = zkServerStr;
        this.retry_interval = retry_interval;
        this.max_retries = max_retries;
    }

    public String getZkServerStr() {
        return zkServerStr;
    }

    public Integer getRetry_interval() {
        return retry_interval;
    }

    public Integer getMax_retries() {
        return max_retries;
    }
}
