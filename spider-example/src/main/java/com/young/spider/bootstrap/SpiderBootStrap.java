package com.young.spider.bootstrap;

import com.young.spider.bootstrap.hook.ShutDownHook;
import com.young.spider.entity.common.SpiderConfig;
import com.young.spider.thread.CrawlerThreadPool;

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
