package com.young.java.examples.clustermanager;

import com.google.common.collect.Lists;
import com.young.java.examples.configcenter.ConfigBean;
import com.young.java.examples.zkclient.IZkChildListener;
import com.young.java.examples.zkclient.ZkClient;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dell on 2016/7/11.
 */
public class ClusterMaster<T extends Serializable> {

    private ZkClient zkClient;

    private String configPath;

    private List<T> nodeList = Lists.newArrayList();

    public ClusterMaster(String zkServer,int timeout,String configPath){
        this.configPath = configPath;
        zkClient = new ZkClient(zkServer,timeout);
    }

    public List<T> getNodeList(){
        return nodeList;
    }

    public void initNode(){
        if(zkClient.exists(configPath)){
            zkClient.delete(configPath);
        }
        zkClient.createPersistent(configPath);
        zkClient.subscribeChildChanges(configPath, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
                List<T> newNodeList = Lists.newArrayList();
                if(configPath.equals(parentPath)){
                    for(String child:currentChilds){
                        String path = configPath+"/"+child;
                        newNodeList.add((T)zkClient.readData(path));
                    }
                }
                nodeList = newNodeList;
            }
        });
    }
    public static void main(String[] args) throws InterruptedException {
        String zkServer = "192.168.31.137:2181";
        int timeout = 5000;
        String path = "/cluster";
        ClusterMaster<ConfigBean> master = new ClusterMaster(zkServer,timeout,path);
        master.initNode();
        for(int i=0;i<10;i++) {
            System.out.println("--------------------------------------");
            for (ConfigBean bean : master.getNodeList()) {
                System.out.println(bean);
            }
            System.out.println("--------------------------------------");
            Thread.sleep(3000);
        }
    }
}
