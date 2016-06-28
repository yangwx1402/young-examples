package com.young.java.examples.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ZookeeperApi {

    private ZooKeeper zooKeeper;


    public ZookeeperApi(String ip, int port) throws IOException {
        zooKeeper = new ZooKeeper(ip + ":" + port, 10000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("receive a event [type=" + watchedEvent.getType() + "],[state=" + watchedEvent.getState() + "],[path=" + watchedEvent.getPath() + "]");
            }
        });
    }


    public void addNode() throws KeeperException, InterruptedException {
        zooKeeper.create("/root1", "yangyong".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZookeeperApi api = new ZookeeperApi("192.168.31.137", 2181);
        api.addNode();
    }
}
