package com.young.java.examples.curator.client;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;


/**
 * Created by yangyong3 on 2017/2/22.
 */
public class ZKClient {
    private CuratorFramework client = null;
    private static final String DEFAULT_ZK_SERVER = "localhost:2181";
    private static final int DEFAULT_RETRY_INTERVAL = 1000;
    private static final int DEFAULT_RETRY_NUM = 3;
    private ZKConfig zkConfig;

    public ZKClient(){
        this(null);
    }

    public ZKClient(ZKConfig config){
        this.zkConfig = config;
        if (this.zkConfig == null) {
            this.zkConfig = new ZKConfig(DEFAULT_ZK_SERVER, DEFAULT_RETRY_INTERVAL, DEFAULT_RETRY_NUM);
        }
        if (client == null) {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(this.zkConfig.getRetry_interval(), zkConfig.getMax_retries());
            client = CuratorFrameworkFactory.newClient(zkConfig.getZkServerStr(), retryPolicy);
            client.start();
        }
    }

    public CuratorFramework getZKClient() {
        return client;
    }
}
