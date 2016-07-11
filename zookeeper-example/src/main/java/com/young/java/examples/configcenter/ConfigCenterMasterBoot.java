package com.young.java.examples.configcenter;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created by dell on 2016/6/30.
 */
public class ConfigCenterMasterBoot {

    public static void main(String[] args) throws InterruptedException {
        String zkServer = "10.16.124.30:2181";
        int timeout = 5000;
        ConfigMaster<ConfigBean> master = new ConfigMaster<>(zkServer, timeout,"/config");
        ConfigBean configBean = new ConfigBean();
        configBean.setIp("127.0.0.1");
        configBean.setPort(8080);
        master.initConfig(configBean);
        Thread.sleep(10000);
        ConfigBean configBean1 = new ConfigBean();
        configBean1.setIp("127.0.0.1");
        configBean1.setPort(9090);
        master.changeData(configBean1);
    }
}
