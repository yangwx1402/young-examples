package com.young.example.spider.spider.crawler.common;

/**
 * Created by yangyong3 on 2017/2/21.
 */
public class CrawlerException extends Exception {
    public CrawlerException(String message) {
        super(message);
    }

    public CrawlerException(String message, Throwable cause) {
        super(message, cause);
    }
}
