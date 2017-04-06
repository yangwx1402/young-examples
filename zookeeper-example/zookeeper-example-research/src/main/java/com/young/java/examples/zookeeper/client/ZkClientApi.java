package com.young.java.examples.zookeeper.client;

import com.young.java.examples.zkclient.IZkDataListener;
import com.young.java.examples.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * Created by Administrator on 2016/7/4.
 */
public class ZkClientApi {

    private ZkClient client;

    public ZkClientApi(String zookeeper,int timeout){
        client = new ZkClient(zookeeper,timeout);
    }

    //创建一个path
    public String createPath(String path,String data,CreateMode mode){
       return client.create(path, data, mode);
    }

    public void changeData(String path,String data){
        client.writeData(path, data);
    }

    /**
     * 注意必须开启多个client才能对数据进行listener,也就是说必须是在分布式环境下,如果单client是监控不到的,
     * 因为单client修改了数据后,在客户端是有数据缓存的,不会触发监听
     * @param path
     * @param listener
     */
    public void nodeChanged(String path,IZkDataListener listener){
        client.subscribeDataChanges(path, listener);
    }


    public static void main(String[] args) throws InterruptedException {
        ZkClientApi api = new ZkClientApi("10.16.124.30:2181",5000);
        String path = "/test";
        //api.createPath(path,"test1",CreateMode.PERSISTENT);
        api.nodeChanged(path, new NodeDataChangedWatcher());
        Thread.sleep(15000);
        api.changeData(path,"test3");
    }
}
