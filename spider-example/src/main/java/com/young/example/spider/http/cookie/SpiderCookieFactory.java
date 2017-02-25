package com.young.example.spider.http.cookie;

import com.young.example.spider.spider.crawler.common.CrawlerType;
import com.young.example.spider.config.ConfigFactory;
import com.young.example.spider.utils.ClassUtils;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class SpiderCookieFactory {

    private static CookieStorage storage;

    private static boolean init = true;

    static {
        try {
            storage = ClassUtils.newInstance(ConfigFactory.getConfig().getSpiderCookie().getStorage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCookie(CrawlerType type) throws CookieException {
        if(init) {
            storage.initCookie();
            init = false;
        }
        return storage.getCookie(type);
    }

    public static void delCookie(CrawlerType type) throws CookieException{
        if(init){
            storage.initCookie();
            init = false;
        }
        storage.removeCookie(type);
    }

}
