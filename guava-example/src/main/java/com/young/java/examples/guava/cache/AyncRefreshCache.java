package com.young.java.examples.guava.cache;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author shazam
 * @DATE 2018/4/17
 */
public class AyncRefreshCache extends CacheExample {

    private ListeningExecutorService executorService = MoreExecutors.listeningDecorator(
        Executors.newFixedThreadPool(20));

    private LoadingCache<String, User> caches = CacheBuilder.newBuilder().maximumSize(100).refreshAfterWrite(10,
        TimeUnit.SECONDS).build(new CacheLoader<String, User>() {
        @Override
        public User load(String key) throws Exception {
            System.out.println("process load");
            return getUserFromDB(key);
        }

        private User getUserFromDB(String key) {
            System.out.println("process getUserFromDB");
            return new User(key, 30);
        }

        @Override
        public ListenableFuture<User> reload(String key, User oldValue) throws Exception {
            return executorService.submit(new Callable<User>() {
                @Override
                public User call() throws Exception {
                    System.out.println("process reload");
                    return getUserFromDB(key);
                }

            });
        }
    });

    @Override
    public User getUser(String name) throws ExecutionException {
        return caches.get(name);
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        AyncRefreshCache cache = new AyncRefreshCache();
        User user = cache.getUser("yangyong");
        System.out.println(user.getName());
        Thread.sleep(5000);
        User user1 = cache.getUser("yangyong");
        System.out.println(user1.getName());
        Thread.sleep(10000);
        User user2 = cache.getUser("yangzhi");
        System.out.println(user2.getName());
        Thread.sleep(2000);
        User user3 = cache.getUser("yangzhi");
        System.out.println(user3.getName());
    }
}
