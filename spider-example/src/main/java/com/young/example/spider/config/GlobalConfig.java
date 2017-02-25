package com.young.example.spider.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class GlobalConfig {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("spider", Locale.getDefault());

    public static final String SPIDER_CONFIG_PATH = bundle.getString("spider.config.path");

    public static final String SPIDER_CONFIG_CLASSNAME = bundle.getString("spider.config.classname");

    public static final String SPIDER_CONFIG_ZKSERVER = bundle.getString("spider.config.zkserver") == null ? "127.0.0.1:2181" : bundle.getString("spider.config.zkserver");

    public static final int SPIDER_CONFIG_ZKINTERVAL = bundle.getString("spider.config.zkinterval") == null ? 1000 : Integer.parseInt(bundle.getString("spider.config.zkinterval"));

    public static final int SPIDER_CONFIG_ZKRETRY = bundle.getString("spider.config.zkretry") == null ? 3 : Integer.parseInt(bundle.getString("spider.config.zkretry"));

}
