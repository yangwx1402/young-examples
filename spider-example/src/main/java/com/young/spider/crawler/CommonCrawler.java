package com.young.spider.crawler;

import com.young.spider.entity.CrawlerEntity;

import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class CommonCrawler implements Crawler {
    @Override
    public CrawlerEntity crawlByGet(String url) {
        return null;
    }

    @Override
    public CrawlerEntity crawlByPost(String url, Map<String, String> parameters) {
        return null;
    }
}
