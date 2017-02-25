package com.young.example.spider.spider.crawler.common;



import com.young.example.spider.spider.entity.common.CrawlerEntity;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public interface Crawler<Meta> {
    public CrawlerEntity crawlByGet(Meta meta) throws CrawlerException;

    public CrawlerEntity crawlByPost(Meta meta) throws CrawlerException;
}
