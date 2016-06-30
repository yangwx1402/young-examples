package com.young.java.examples.configcenter;

import com.young.examples.common.json.JsonUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by dell on 2016/6/30.
 */
public class ConfigClient {
    private ZooKeeper zookeeper;

    private static final int timeout = 10000;

    public ConfigClient(String ip, int port) throws IOException {
        zookeeper = new ZooKeeper(ip + ":" + port, timeout, new ConfigWatcher());
    }

    public <T> T getConfig(final String path, final Class<T> tClass) throws KeeperException, InterruptedException, IOException {
        String json = new String(zookeeper.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent.getType());
                if(watchedEvent.getType()== Event.EventType.NodeDataChanged){

                }
            }
        }, null));
        return JsonUtils.json2Object(json, tClass);

    }


    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ConfigClient client = new ConfigClient("10.16.124.30", 2181);
        String configPath = "/config";
        ConfigBean bean = client.getConfig(configPath, ConfigBean.class);
        for (int i = 0; i < 10; i++) {
            System.out.println(bean.getIp() + ":" + bean.getPort());
            Thread.sleep(6000);
        }
    }
}
