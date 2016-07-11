package com.young.java.examples.clustermanager;

import com.young.java.examples.zkclient.ZkClient;

/**
 * Created by dell on 2016/7/11.
 */
public class ClusterMaster {

    private ZkClient zkClient;

    private String configPath;

    public ClusterMaster(String zkServer,int timeout,String configPath){
        this.configPath = configPath;
        zkClient = new ZkClient(zkServer,timeout);
    }

    public void initNode(){
        
    }
}
