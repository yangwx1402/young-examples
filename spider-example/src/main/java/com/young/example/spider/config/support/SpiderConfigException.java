package com.young.example.spider.config.support;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class SpiderConfigException extends Exception {
    public SpiderConfigException(String message) {
        super(message);
    }

    public SpiderConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
