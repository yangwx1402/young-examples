package com.young.example.spider.config;

import com.young.example.spider.config.support.SpiderConfigException;
import com.young.example.spider.config.support.LocalFileConfigStorage;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class ConfigStorageExample {

    public void getStorageFromLocal() throws SpiderConfigException {
        String localFile = "D:\\le_eco\\customer-voice\\le.data.scs\\le.data.scs.spider\\src\\main\\resources\\spider-config.xml";
        ConfigStorage storage = new LocalFileConfigStorage(localFile);
        System.out.println(storage.getSpiderConfig().getMessageQueue().getClassname());
    }

    public static void main(String[] args) throws SpiderConfigException {
        ConfigStorageExample example = new ConfigStorageExample();
        example.getStorageFromLocal();
    }
}
