package com.young.example.spider.config.support;

import com.young.example.spider.config.ConfigStorage;
import com.young.example.spider.zkclient.ZKClientFactory;
import com.young.example.spider.config.common.SpiderConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class ZKConfigStorage extends AbstractStorage implements ConfigStorage {

    private static final Logger log = LogManager.getLogger(ZKConfigStorage.class);

    public ZKConfigStorage(String configFilePath) {
        super(configFilePath);
    }

    @Override
    public SpiderConfig getSpiderConfig() throws SpiderConfigException {
        String configData = null;
        SpiderConfig spiderConfig = null;
        try {
            byte[] bytes = ZKClientFactory.getZKClient().getData().forPath(configFilePath);
            log.info("read config xml from zk path is -"+configFilePath+"]");
            if (bytes != null)
                configData = new String(bytes, "utf-8");
            if (!StringUtils.isBlank(configData)) {
                spiderConfig = xml.fromXml(configData, SpiderConfig.class);
            }
        } catch (Exception e) {
            throw new SpiderConfigException("read zk path [" + configFilePath + "] error ", e);
        }
        return spiderConfig;
    }
}
