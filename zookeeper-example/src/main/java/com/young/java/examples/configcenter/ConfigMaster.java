package com.young.java.examples.configcenter;

import com.young.java.examples.zkclient.ZkClient;

import java.io.Serializable;

/**
 * Created by dell on 2016/6/30.
 */
public class ConfigMaster<T extends Serializable> {

    private ZkClient zkClient;

    private String configPath;

    public ConfigMaster(String zkServer,int timeout,String configPath){
        this.configPath = configPath;
        zkClient = new ZkClient(zkServer,timeout);
    }

    public void initConfig(T configObject){
        if(zkClient.exists(configPath)){
            zkClient.delete(configPath);
        }
        zkClient.createPersistent(configPath,configObject);
    }

    public void changeData(T configObject){
        zkClient.writeData(configPath,configObject);
    }
}
