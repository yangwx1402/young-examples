package com.young.example.spider.distribution.task.leadertask;

import com.young.example.spider.distribution.task.DistributedTaskException;

/**
 * Created by young.yang on 2017/2/25.
 */
public interface LeaderTask<T> {
    public T runTask() throws DistributedTaskException;
}
