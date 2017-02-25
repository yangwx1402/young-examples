package com.young.example.spider.config.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangyong3 on 2017/2/16.
 */
@XStreamAlias("spider")
public class SpiderConfig implements Serializable {

    private List<SpiderProperty> properties;

    private SpiderMessageQueue messageQueue;

    @XStreamAlias("cookies")
    private SpiderCookie spiderCookie;

    private List<SpiderThread> threads;

    public List<SpiderProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<SpiderProperty> properties) {
        this.properties = properties;
    }

    public SpiderMessageQueue getMessageQueue() {
        return messageQueue;
    }

    public void setMessageQueue(SpiderMessageQueue messageQueue) {
        this.messageQueue = messageQueue;
    }

    public SpiderCookie getSpiderCookie() {
        return spiderCookie;
    }

    public void setSpiderCookie(SpiderCookie spiderCookie) {
        this.spiderCookie = spiderCookie;
    }

    public List<SpiderThread> getThreads() {
        return threads;
    }

    public void setThreads(List<SpiderThread> threads) {
        this.threads = threads;
    }
}
