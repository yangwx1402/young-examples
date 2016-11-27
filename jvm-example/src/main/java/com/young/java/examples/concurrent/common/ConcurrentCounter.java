package com.young.java.examples.concurrent.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by young.yang on 2016/11/27.
 * 计数器
 */
public class ConcurrentCounter {
    public static class Counter implements Runnable{

        private AtomicInteger count;

        public Counter(AtomicInteger count){
            this.count = count;
        }

        @Override
        public void run() {
            for(int i=0;i<10;i++){
                count.incrementAndGet();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
       AtomicInteger count = new AtomicInteger(0);
        for(int i=0;i<10;i++){
            executor.execute(new Counter(count));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(count.get());
    }
}
