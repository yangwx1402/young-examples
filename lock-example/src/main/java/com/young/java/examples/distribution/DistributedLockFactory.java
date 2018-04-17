package com.young.java.examples.distribution;

import java.util.concurrent.locks.Lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author shazam
 * @DATE 2018/4/14
 */
public class DistributedLockFactory {

    private CuratorFramework curatorFramework;

    private static int retry = 10;

    private static int retryInterval = 1000;

    private static String zkString = "127.0.0.1:2121";

    public void setRetry(int retry) {
        DistributedLockFactory.retry = retry;
    }

    public void setRetryInterval(int retryInterval) {
        DistributedLockFactory.retryInterval = retryInterval;
    }

    public void init() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(retryInterval, retry);
        this.curatorFramework = CuratorFrameworkFactory.newClient(zkString, retryPolicy);
        this.curatorFramework.start();
    }

    public void destory() {
        this.curatorFramework.close();
    }

    public Lock getLock() {
        return new DistributedLock();
    }
}
