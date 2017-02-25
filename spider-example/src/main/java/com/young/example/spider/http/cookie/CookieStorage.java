package com.young.example.spider.http.cookie;

import com.young.example.spider.spider.crawler.common.CrawlerType;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public interface CookieStorage {

    public void initCookie() throws CookieException;

    public String getCookie(CrawlerType type) throws CookieException;

    public void removeCookie(CrawlerType type) throws CookieException;
}
