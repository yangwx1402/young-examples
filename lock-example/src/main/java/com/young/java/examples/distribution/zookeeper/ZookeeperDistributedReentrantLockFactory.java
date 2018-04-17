package com.young.java.examples.distribution.zookeeper;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author shazam
 * @DATE 2018/4/17
 */
public class ZookeeperDistributedReentrantLockFactory {

    private static final String BASE_ZOOKEEPER_LOCK_PATH = "/distributed";

    private static CuratorFramework curatorFramework;

    private static int retry = 10;

    private static int retryInterval = 1000;

    private static String zkString = "127.0.0.1:2181";

    public static void setRetry(int retry) {
        ZookeeperDistributedReentrantLockFactory.retry = retry;
    }

    public static void setRetryInterval(int retryInterval) {
        ZookeeperDistributedReentrantLockFactory.retryInterval = retryInterval;
    }

    public static void setZkString(String zkString) {
        ZookeeperDistributedReentrantLockFactory.zkString = zkString;
    }

    public static void init() {
        if (curatorFramework == null) {
            RetryPolicy retryPolicy = new ExponentialBackoffRetry(retryInterval, retry);
            curatorFramework = CuratorFrameworkFactory.newClient(zkString, retryPolicy);
            curatorFramework.start();
        }
    }

    public static ZookeeperDistributedReentrantLock createLock(String lockPath) {
        return new ZookeeperDistributedReentrantLock(lockPath);
    }

    public static class ZookeeperDistributedReentrantLock implements Lock {

        private String lockPath;

        private InterProcessMutex processMutex;

        public ZookeeperDistributedReentrantLock(String lockPath) {
            this.lockPath = lockPath;
            processMutex = new InterProcessMutex(curatorFramework,
                BASE_ZOOKEEPER_LOCK_PATH + File.separator + lockPath);
        }

        private void checkState() {
            if (curatorFramework.getState() != CuratorFrameworkState.STARTED) {
                throw new RuntimeException("curator client is not started");
            }
        }

        @Override
        public void lock() {
            checkState();
            try {
                this.processMutex.acquire();
            } catch (Exception e) {
                throw new RuntimeException("get lock error", e);
            }
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {
            throw new RuntimeException("not supported");
        }

        @Override
        public boolean tryLock() {
            throw new RuntimeException("not supported");
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            checkState();
            try {
                return this.processMutex.acquire(time, unit);
            } catch (Exception e) {
                throw new RuntimeException("get lock error", e);
            }
        }

        @Override
        public void unlock() {
            checkState();
            try {
                this.processMutex.release();
            } catch (Exception e) {
                throw new RuntimeException("unlock error", e);
            }
        }

        @Override
        public Condition newCondition() {
            throw new RuntimeException("not supported");
        }
    }

}
