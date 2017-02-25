package com.young.example.spider.spider.crawler.common;

import java.util.Comparator;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public enum CrawlerType implements Comparator<CrawlerType> {

    JD("JD", 10), TMAILL("TMALL", 10);

    private String name;

    private Integer priority;

    CrawlerType(String name, Integer priority) {
        this.name = name;
        this.priority = priority;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compare(CrawlerType o1, CrawlerType o2) {
        return o1.getPriority().compareTo(o2.getPriority());
    }
}
