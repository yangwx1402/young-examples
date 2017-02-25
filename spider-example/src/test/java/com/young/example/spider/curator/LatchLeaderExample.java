package com.young.example.spider.curator;

import com.young.example.spider.zkclient.ZKClientFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderLatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class LatchLeaderExample {

    private static final String leader_path = "/leader";
    public void leaderWork() throws Exception {
        CuratorFramework client = ZKClientFactory.getZKClient();
        LeaderLatch latch = new LeaderLatch(client, leader_path);
        latch.start();
        for(int i=0;i<100;i++){
            latch.await(2, TimeUnit.SECONDS);
            if(latch.hasLeadership()){
                System.out.println("我是领导，我发任务，你们别抢");
            }else{
                System.out.println("我不是领导，啥也干不了");
            }
            Thread.sleep(1000*20);
        }
    }
    public static void main(String[] args) throws Exception {
        LatchLeaderExample example = new LatchLeaderExample();
        example.leaderWork();
    }
}
