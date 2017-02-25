package com.young.example.spider.http.cookie.support;

import com.young.example.spider.config.ConfigFactory;
import com.young.example.spider.distribution.storage.DistributeStorage;
import com.young.example.spider.distribution.storage.StorageException;
import com.young.example.spider.distribution.storage.support.ZKStorage;
import com.young.example.spider.spider.crawler.common.CrawlerType;
import com.young.example.spider.config.common.SpiderCookieLogin;
import com.young.example.spider.http.cookie.CookieException;
import com.young.example.spider.http.cookie.CookieStorage;
import com.young.example.spider.zkclient.ZKClientFactory;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class ZkCookieStorage extends AbstractCookieStorage implements CookieStorage {

    private static final DistributeStorage<String> storage = new ZKStorage<String>();

    @Override
    public void initCookie() throws CookieException {
        CuratorFramework client = ZKClientFactory.getZKClient();
        InterProcessMutex lock = null;
        try {
            lock = new InterProcessMutex(client, ConfigFactory.getConfig().getMessageQueue().getQueueNamePrefix() + "/tmp/store/cookie");
            if (lock.acquire(1, TimeUnit.MINUTES)) {
                try {
                    for (SpiderCookieLogin login : ConfigFactory.getConfig().getSpiderCookie().getLogins()) {
                        getCookie(login, true);
                    }
                } catch (Exception e) {
                    throw new CookieException("init cookie error", e);
                } finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        throw new CookieException("init cookie error", e);
                    }
                }
            }
        } catch (Exception e) {
            throw new CookieException("init cookie error", e);
        }
    }

    @Override
    public String getCookie(CrawlerType type) throws CookieException {
        try {
            return getCookie(ConfigFactory.getLogin(type.toString()), false);
        } catch (Exception e) {
            throw new CookieException("get Cookie error",e);
        }
    }

    @Override
    public void removeCookie(CrawlerType type) throws CookieException {
        try {
            removeCookie(ConfigFactory.getLogin(type.toString()));
        } catch (Exception e) {
            throw new CookieException("remove Cookie error",e);
        }
    }

    @Override
    public String readCookie(String storePath) throws CookieException {
        try {
            return storage.get(storePath,String.class);
        } catch (StorageException e) {
            throw new CookieException("read cookie error", e);
        }
    }

    @Override
    public void writeBack(String storePath, String cookieJson) throws CookieException {
        try {
            storage.store(storePath,cookieJson,true);
        } catch (StorageException e) {
            throw new CookieException("write cookie error", e);
        }
    }

    @Override
    public void delCookie(String storePath) throws CookieException {
        try {
            storage.del(storePath);
        } catch (StorageException e) {
            throw new CookieException("del cookie from "+storePath+" error",e);
        }
    }
}
