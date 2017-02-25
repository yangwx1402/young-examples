package com.young.example.spider.zkclient;

import com.young.example.spider.config.GlobalConfig;
import org.apache.curator.framework.CuratorFramework;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class ZKClientFactory {

    private static final ZKClient zkClient = new ZKClient(new ZKConfig(GlobalConfig.SPIDER_CONFIG_ZKSERVER, GlobalConfig.SPIDER_CONFIG_ZKINTERVAL, GlobalConfig.SPIDER_CONFIG_ZKRETRY));

    public static CuratorFramework getZKClient() {
        return zkClient.getZKClient();
    }
}
