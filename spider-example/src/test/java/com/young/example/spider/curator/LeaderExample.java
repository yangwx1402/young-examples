package com.young.example.spider.curator;

import com.young.example.spider.zkclient.ZKClientFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;

import java.util.Scanner;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class LeaderExample {

    private static final String path = "/leader_selector";

    public void leadwork() throws InterruptedException {
        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {

            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                while(true) {
                    System.out.println("我是领导，我发任务，你们别抢");
                    Thread.sleep(3000);
                }
            }

            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState) {
                System.out.println("状态发生了改变");
            }

        };
        CuratorFramework client = ZKClientFactory.getZKClient();
        client.getConnectionStateListenable().addListener(new ZkConnectionStateListenerExample());
        LeaderSelector selector = new LeaderSelector(client, path, listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();
        new Scanner(System.in).nextLine();
    }

    public static void main(String[] args) throws InterruptedException {
        LeaderExample example = new LeaderExample();
        example.leadwork();
    }
}
