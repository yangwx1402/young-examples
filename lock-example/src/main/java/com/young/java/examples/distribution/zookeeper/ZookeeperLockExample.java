package com.young.java.examples.distribution.zookeeper;

import java.util.concurrent.TimeUnit;

import com.young.java.examples.distribution.zookeeper.ZookeeperDistributedReentrantLockFactory
    .ZookeeperDistributedReentrantLock;

/**
 * @author shazam
 * @DATE 2018/4/17
 */
public class ZookeeperLockExample {
    public static void main(String[] args) throws InterruptedException {
        ZookeeperDistributedReentrantLockFactory.init();
        ZookeeperDistributedReentrantLock lock = ZookeeperDistributedReentrantLockFactory.createLock("lock");
        lock.tryLock(10, TimeUnit.SECONDS);
    }
}
