package com.young.java.examples.guava.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 自动刷新的缓存，多线程访问情况下，只会有一个现场调用load方法刷新缓存，其他线程仍旧获取旧的缓存值，可以更好的保护后台DB
 *
 * @author shazam
 * @DATE 2018/4/17
 */
public class RefreshCache extends CacheExample{
    private LoadingCache<String, User> caches = CacheBuilder.newBuilder().maximumSize(100).refreshAfterWrite(10,
        TimeUnit.SECONDS).build(new CacheLoader<String, User>() {
        @Override
        public User load(String key) throws Exception {
            User user = getUserFromDb(key);
            System.out.println("get user from db key is " + key);
            return user;
        }

        private User getUserFromDb(String key) {
            return new User(key, 30);
        }
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RefreshCache cache = new RefreshCache();
        User user = cache.getUser("yangyong");
        System.out.println(user.getName());
        Thread.sleep(5000);
        User user1 = cache.getUser("yangyong");
        System.out.println(user1.getName());
        Thread.sleep(10000);
        User user2 = cache.getUser("yangzhi");
        System.out.println(user2.getName());
    }

    @Override
    public User getUser(String name) throws ExecutionException {
        User user = caches.get(name);
        return user;
    }
}
