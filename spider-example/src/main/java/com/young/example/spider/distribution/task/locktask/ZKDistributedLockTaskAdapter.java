package com.young.example.spider.distribution.task.locktask;

import com.young.example.spider.zkclient.ZKClientFactory;
import com.young.example.spider.distribution.task.DistributedTaskException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

/**
 * Created by young.yang on 2017/2/25.
 */
public abstract class ZKDistributedLockTaskAdapter<T> implements DistributedTask<T> {

    public abstract <T> T task() throws Exception;

    private static final String base_lock_path = "/spider/lock/";

    private int timeout = 1;

    private TimeUnit timeUnit = TimeUnit.MINUTES;

    public ZKDistributedLockTaskAdapter(int timeout, TimeUnit timeUnit){
         this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    @Override
    public T runTask() throws DistributedTaskException {
        CuratorFramework client = ZKClientFactory.getZKClient();
        InterProcessMutex lock = null;
        try {
            lock = new InterProcessMutex(client, base_lock_path + this.getClass().getName());
            if (lock.acquire(timeout, timeUnit)) {
                try {
                     return task();
                } catch (Exception e) {
                    throw new DistributedTaskException("run task error", e);
                } finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        throw new DistributedTaskException("release lock error", e);
                    }
                }
            }
        } catch (Exception e) {
            throw new DistributedTaskException("get lock error", e);
        }
        throw new DistributedTaskException("run task error");
    }
}
