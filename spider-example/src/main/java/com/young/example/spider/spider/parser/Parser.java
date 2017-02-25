package com.young.example.spider.spider.parser;


import com.young.example.spider.spider.entity.common.CrawlerEntity;
import com.young.example.spider.spider.entity.common.ParserEntity;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public interface Parser<DATA,META> {
    public ParserEntity<DATA> parser(CrawlerEntity<META> crawlerEntity) throws ParserException;
}
