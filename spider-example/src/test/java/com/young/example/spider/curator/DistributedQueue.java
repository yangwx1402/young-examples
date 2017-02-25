package com.young.example.spider.curator;

import com.young.example.spider.zkclient.ZKClientFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.queue.SimpleDistributedQueue;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class DistributedQueue {

    private static final String data_path = "/queue";

    private SimpleDistributedQueue queue;

    public DistributedQueue(){
        CuratorFramework client = ZKClientFactory.getZKClient();
        queue = new SimpleDistributedQueue(client,data_path);
    }

    public void offer() throws Exception {
         for(int i=0;i<100;i++){
             queue.offer(("yangyong_"+i).getBytes());
         }
    }

    public void take() throws Exception {
         for(int i=0;i<10;i++){
             System.out.println(new String(queue.take()));
             Thread.sleep(10000);
         }
    }

    public static void main(String[] args) throws Exception {
        DistributedQueue queue = new DistributedQueue();
        //queue.offer();
        queue.take();
    }
}
