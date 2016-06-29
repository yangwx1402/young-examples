package com.young.java.examples.zookeeper;

import com.young.java.examples.zookeeper.watcher.ExistWatcher;
import com.young.java.examples.zookeeper.watcher.ZookeeperWatcher;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/28.
 */
public class ZookeeperApi {

    private ZooKeeper zooKeeper;


    public ZookeeperApi(String ip, int port) throws IOException {
        zooKeeper = new ZooKeeper(ip + ":" + port, 10000, new ZookeeperWatcher());
    }


    public void addNode(String path, String data, ArrayList<ACL> acls, CreateMode createMode) throws KeeperException, InterruptedException {
        //同步创建节点,成功后返回节点地址
        String nodePath = zooKeeper.create(path, data.getBytes(), acls, createMode);
        System.out.println(nodePath);
        //异步创建节点
//        zooKeeper.create("/root2", "yangyong".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new AsyncCallback.StringCallback() {
//            @Override
//            public void processResult(int i, String s, Object o, String s1) {
//            }
//        },"");
    }

    public void exist(String path,Watcher watcher) throws KeeperException, InterruptedException {
        //这里就会采用默认的创建Zookeeper对象时候的Watcher
        //zooKeeper.exists(path,true);
        //指定一个特殊的watcher
        zooKeeper.exists(path,watcher);
    }

    //version==-1为删除所有的数据
    public void delete(String path,int version) throws KeeperException, InterruptedException {
        zooKeeper.delete(path,version);
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZookeeperApi api = new ZookeeperApi("10.16.124.30", 2181);
        api.addNode("/root1","yangyong",ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        api.exist("/root1",new ExistWatcher());
        api.delete("/root1",-1);
    }
}
