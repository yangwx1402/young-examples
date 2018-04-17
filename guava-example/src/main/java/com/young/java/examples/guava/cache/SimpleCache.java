package com.young.java.examples.guava.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * 简单缓存，主动刷新，这样高并发场景下如果大量Key同时超时，容易造成雪崩，压垮后台DB
 *
 * @author shazam
 * @DATE 2018/4/17
 */
public class SimpleCache extends CacheExample {

    /**
     * 简单缓存,设置缓存大小，超时时间，CacheLoader为key超时或者无法获取缓存值的时候执行
     */
    private LoadingCache<String, User> caches = CacheBuilder.newBuilder().maximumSize(100).expireAfterWrite(10,
        TimeUnit.SECONDS).build(new CacheLoader<String, User>() {

        /**
         * load方法会在缓存失效，或者缓存值不存在时调用
         * @param key
         * @return
         * @throws Exception
         */
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

    @Override
    public User getUser(String name) throws ExecutionException {
        User user = caches.get(name);
        return user;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SimpleCache cache = new SimpleCache();
        User user = cache.getUser("yangyong");
        System.out.println(user.getName());
        Thread.sleep(5000);
        User user1 = cache.getUser("yangyong");
        System.out.println(user1.getName());
        Thread.sleep(6000);
        User user2 = cache.getUser("yangzhi");
        System.out.println(user2.getName());
    }
}
