package com.young.example.spider.curator;

import com.young.example.spider.zkclient.ZKClientFactory;
import org.apache.curator.framework.CuratorFramework;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class CuratorZKClientExample {
    public void testClient() throws Exception {
        CuratorFramework client = ZKClientFactory.getZKClient();
        client.create().forPath("/yangyong","yangyong".getBytes());
        client.close();
    }
    public static void main(String[] args) throws Exception {
        CuratorZKClientExample example = new CuratorZKClientExample();
        example.testClient();
    }
}
