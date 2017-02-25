package com.young.example.spider.config;

import com.young.example.spider.config.common.SpiderConfig;
import com.young.example.spider.config.support.SpiderConfigException;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public interface ConfigStorage {
    public SpiderConfig getSpiderConfig() throws SpiderConfigException;
}
