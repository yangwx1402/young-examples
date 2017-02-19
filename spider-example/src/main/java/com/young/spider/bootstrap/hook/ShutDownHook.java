package com.young.spider.bootstrap.hook;

import com.young.spider.counter.CounterTool;
import com.young.spider.thread.CrawlerThreadPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * Created by yangyong3 on 2017/2/16.
 */
public class ShutDownHook extends Thread {
    private long starttime;

    private CrawlerThreadPool threadPool;

    private static final long one_minute = 1000 * 60;

    private static final long one_hour = one_minute * 60;

    private static final Logger logger = LogManager.getLogger(ShutDownHook.class);

    public ShutDownHook(long starttime, CrawlerThreadPool threadPool) {
        this.starttime = starttime;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        threadPool.shutdown(true);
        long duration = System.currentTimeMillis() - starttime;
        if (duration < one_minute) {
            logger.info("log spider is stopped, now time is " + new Date() + ", spider run duration " + (duration / one_minute) + " minutes");
            logger.info("spider counter info is "+ CounterTool.print());
        } else {
            logger.info("log spider is stopped, now time is " + new Date() + ", spider run duration " + (duration / one_hour) + " hours");
            logger.info("spider counter info is "+ CounterTool.print());
        }
    }
}
