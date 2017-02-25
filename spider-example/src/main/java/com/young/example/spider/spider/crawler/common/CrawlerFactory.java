package com.young.example.spider.spider.crawler.common;

import com.young.example.spider.config.BaseFactory;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class CrawlerFactory {

    public synchronized static <Meta> Crawler<Meta> getCrawler(String classname) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return BaseFactory.getInstance(classname);
    }
}
