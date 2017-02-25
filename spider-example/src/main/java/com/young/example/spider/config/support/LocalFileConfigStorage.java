package com.young.example.spider.config.support;

import com.young.example.spider.config.ConfigStorage;
import com.young.example.spider.config.common.SpiderConfig;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class LocalFileConfigStorage extends AbstractStorage implements ConfigStorage {

    private static final Logger log = LogManager.getLogger(LocalFileConfigStorage.class);

    public LocalFileConfigStorage(String configFilePath) {
        super(configFilePath);
    }

    @Override
    public SpiderConfig getSpiderConfig() throws SpiderConfigException {
        String fileData = null;
        SpiderConfig spiderConfig = null;
        try {
            fileData = FileUtils.readFileToString(new File(configFilePath), "utf-8");
            spiderConfig = xml.fromXml(fileData, SpiderConfig.class);
            log.info("read config file from local path is -["+configFilePath+"]");
        } catch (IOException e) {
            throw new SpiderConfigException("read config file [" + configFilePath + "] error", e);
        }
        return spiderConfig;
    }
}
