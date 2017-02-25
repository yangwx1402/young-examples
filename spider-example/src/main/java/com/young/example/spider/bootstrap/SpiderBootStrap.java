package com.young.example.spider.bootstrap;


import com.young.example.spider.bootstrap.hook.ShutDownHook;
import com.young.example.spider.config.common.SpiderConfig;
import com.young.example.spider.spider.thread.CrawlerThreadPool;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class SpiderBootStrap {
    public void run(SpiderConfig config) {
        CrawlerThreadPool threadPool = new CrawlerThreadPool();
        long start = System.currentTimeMillis();
        Runtime.getRuntime().addShutdownHook(new ShutDownHook(start, threadPool));

    }
}
