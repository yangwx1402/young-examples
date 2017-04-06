package com.young.java.examples.clustermanager;

import com.young.java.examples.configcenter.ConfigBean;
import com.young.java.examples.zkclient.ZkClient;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by dell on 2016/7/11.
 */
public class ClusterSlave<T extends Serializable> {

    private ZkClient zkClient;

    private String configPath;

    public ClusterSlave(String zkServer,int timeout,String configPath){
        this.configPath = configPath;
        zkClient = new ZkClient(zkServer,timeout);
    }

    public void addNode(T nodeInfo){
        zkClient.createEphemeralSequential(configPath + "/slave", nodeInfo);
    }
    public static void main(String[] args) throws UnknownHostException, InterruptedException {
        String zkServer = "192.168.31.137:2181";
        int timeout = 5000;
        String path = "/cluster";
        ClusterSlave slave = new ClusterSlave(zkServer,timeout,path);
        ConfigBean configBean = new ConfigBean();
        configBean.setPort(8080);
        configBean.setIp(InetAddress.getLocalHost().getHostAddress());
        slave.addNode(configBean);
        Thread.sleep(30000);
    }
}
