package com.young.spring.rest.test;

import com.young.spring.rest.asynctask.AsyncTaskExample;
import com.young.spring.rest.config.BootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by yangyong3 on 2017/2/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootConfig.class)
public class AsyncTaskTest {
    @Autowired
    private AsyncTaskExample taskExample;

    @Test
    public void test() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        Future<String> step1 = taskExample.step1();
        Future<String> step2 = taskExample.step2();
        Future<String> step3 = taskExample.step3();
        while (true) {
            if (step1.isDone() && step2.isDone() && step3.isDone())
                break;
            else
                Thread.sleep(100);
        }
        System.out.println("task run over cost time -"+(System.currentTimeMillis()-start));
        System.out.println(step1.get());
        System.out.println(step2.get());
        System.out.println(step3.get());
        /**从输出结果中能看出来异步并发提高了执行效率
         * task run over cost time -9104
         step1 run over cost time -6000
         step2 run over cost time -9000
         step3 run over cost time -5000
         */

    }
}
