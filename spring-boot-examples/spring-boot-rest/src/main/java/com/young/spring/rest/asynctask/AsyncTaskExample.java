package com.young.spring.rest.asynctask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by yangyong3 on 2017/2/4.
 */
@Component
public class AsyncTaskExample {
    @Async
    public Future<String> step1(){
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<String>("step1 run over cost time -"+(System.currentTimeMillis()-start));
    }

    @Async
    public Future<String> step2(){
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<String>("step2 run over cost time -"+(System.currentTimeMillis()-start));
    }

    @Async
    public Future<String> step3(){
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AsyncResult<String>("step3 run over cost time -"+(System.currentTimeMillis()-start));
    }
}
