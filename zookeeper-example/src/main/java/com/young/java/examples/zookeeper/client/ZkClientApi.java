package com.young.java.examples.zookeeper.client;

import com.young.java.examples.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * Created by Administrator on 2016/7/4.
 */
public class ZkClientApi {
    public void test(){
        ZkClient client = new ZkClient("192.168.31.137:2181",5000);
        client.create("/test","test", CreateMode.PERSISTENT);
    }
    public static void main(String[] args){
        ZkClientApi api = new ZkClientApi();
        api.test();
    }
}
