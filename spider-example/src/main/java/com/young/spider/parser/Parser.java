package com.young.spider.parser;

import com.young.spider.entity.CrawlerEntity;
import com.young.spider.entity.ParserEntity;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public interface Parser {
    public ParserEntity parser(CrawlerEntity crawlerEntity);
}
