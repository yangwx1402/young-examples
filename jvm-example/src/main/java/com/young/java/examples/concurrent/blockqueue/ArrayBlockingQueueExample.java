package com.young.java.examples.concurrent.blockqueue;

/**
 * Created by young.yang on 2016/11/8.
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞队列,与LinkedBlockingQueue类似 不过Linked是以链接为底层实现
 */
public class ArrayBlockingQueueExample {
    public void example() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
        for(int i=0;i<10;i++){
            //add超限后是會報異常滴,同樣remove和element一樣
            queue.add(i);
        }
        for(int i=0;i<11;i++){
            //poll超限后並不會拋異常，而是返回null,同樣offer和peek也一樣
            System.out.println(queue.poll());
        }
        //此時queue已經空了
        System.out.println(queue);
        //put超限后線程就阻塞了，直到可以繼續put,同樣take也一樣
        for (int i = 0; i < 10; i++) {
            queue.put(i);
        }
        //同時offer和poll這一組方法可以設置超時時間,在超時后返回
        System.out.println(queue.offer(11,1, TimeUnit.SECONDS));
        queue.clear();
        System.out.println(queue.poll(1,TimeUnit.SECONDS));
        //不能向queue中添加null，會報空指針
        //queue.add(null);
        queue.add(1);
        //由於底層是採用Array來實現,所以remove元素的開銷比較大
        System.out.println(queue.remove(1));
    }

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueueExample example = new ArrayBlockingQueueExample();
        example.example();
    }
}
