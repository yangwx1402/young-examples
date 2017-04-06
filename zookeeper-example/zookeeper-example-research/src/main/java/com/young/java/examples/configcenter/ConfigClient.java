package com.young.java.examples.configcenter;

import com.young.java.examples.zkclient.IZkDataListener;
import com.young.java.examples.zkclient.ZkClient;

import java.io.Serializable;

/**
 * Created by dell on 2016/6/30.
 */
public class ConfigClient<T extends Serializable> {

    private ConfigBean configInfo;

    private ZkClient zkClient;

    private String configPath;

    public ConfigClient(String zkServer,int timeout,String configPath){
        this.configPath = configPath;
        zkClient = new ZkClient(zkServer,timeout);
        listenerConfigDataChange();
    }

    public T getConfig(){
        return zkClient.readData(configPath);
    }

    private void listenerConfigDataChange(){
        zkClient.subscribeDataChanges(configPath, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                if(dataPath.equals(configPath)){
                    configInfo = (ConfigBean) data;
                }
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                 if(dataPath.equals(configPath)){
                     configInfo = null;
                 }
            }
        });
    }

}
