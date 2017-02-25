package com.young.example.spider.config.support;

import com.young.example.spider.config.common.*;
import com.young.example.spider.utils.XmlUtils;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public abstract class AbstractStorage {

    protected String configFilePath;

    public AbstractStorage(String configFilePath){
        this.configFilePath = configFilePath;
    }

    protected static final XmlUtils xml = new XmlUtils(new Class[]{SpiderConfig.class, SpiderCookie.class, SpiderCookieLogin.class, SpiderMessageQueue.class, SpiderParam.class,SpiderProperty.class,SpiderThread.class});
}
