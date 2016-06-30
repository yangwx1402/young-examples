package com.young.java.examples.configcenter;

import com.young.examples.common.json.JsonUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by dell on 2016/6/30.
 */
public class ConfigMaster {

    private ZooKeeper zookeeper;

    private static final int timeout = 10000;

    public ConfigMaster(String ip,int port) throws IOException {
        zookeeper = new ZooKeeper(ip+":"+port,timeout,new ConfigWatcher());
    }

    public <T> void initConfig(String path,T configData,ArrayList<ACL> acls, CreateMode createMode) throws IOException, KeeperException, InterruptedException {
        zookeeper.create(path, JsonUtils.toJson(configData).getBytes(),acls,createMode);
    }

    public <T> void changeConfig(String path,T configData) throws IOException, KeeperException, InterruptedException {
        zookeeper.setData(path,JsonUtils.toJson(configData).getBytes(),-1);
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        String configPath = "/config";
        ConfigMaster master = new ConfigMaster("10.16.124.30",2181);
        ConfigBean bean = new ConfigBean();
        bean.setIp("localhost");
        bean.setPort(2181);
        master.initConfig(configPath,bean, ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
        for(int i=0;i<10;i++){
            Thread.sleep(5000);
            bean.setPort(3000+i);
            master.changeConfig(configPath,bean);
        }

    }
}
