package com.young.example.spider.spider.thread;


import org.apache.log4j.Logger;

import java.util.concurrent.*;

/**
 * Created by yangyong3 on 2016/12/4.
 * 爬虫线程池
 */
public class CrawlerThreadPool {

    private static final Logger log = Logger.getLogger(CrawlerThreadPool.class);

    private ThreadPoolExecutor threadPool;

    public CrawlerThreadPool(int core, int max, long alived, TimeUnit timeUnit, BlockingQueue<Runnable> queue) {
        this.threadPool = new ThreadPoolExecutor(core, max, alived, timeUnit, queue);
    }

    public CrawlerThreadPool() {
        this(20, 20, 1, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());
    }

    public void execute(Runnable run) {
        threadPool.execute(run);
    }

    public void submit(Runnable run) {
        threadPool.submit(run);
    }

    public <V> Future<V> submit(Callable<V> call){
        return threadPool.submit(call);
    }

    public void shutdown(boolean now) {
        if (now)
            threadPool.shutdownNow();
        else
            threadPool.shutdown();
    }

    public void monitor(int wait, TimeUnit unit) throws InterruptedException {
        long start = System.currentTimeMillis();
        while (true) {
            if (threadPool.awaitTermination(wait, unit)) {
                log.info("thread pool executor over cost time -" + (System.currentTimeMillis() - start));
                break;
            }
        }
    }
}
