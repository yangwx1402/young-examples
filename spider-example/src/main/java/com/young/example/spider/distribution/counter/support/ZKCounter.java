package com.young.example.spider.distribution.counter.support;

import com.young.example.spider.distribution.counter.CounterAdapter;
import com.young.example.spider.zkclient.ZKClientFactory;
import com.young.example.spider.distribution.counter.CounterException;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;

/**
 * Created by young.yang on 2017/2/25.
 */
public class ZKCounter extends CounterAdapter {

    private DistributedAtomicLong atomicLong;

    public ZKCounter(String counter) {
        super(counter);
        this.atomicLong = new DistributedAtomicLong(ZKClientFactory.getZKClient(),counter,ZKClientFactory.getZKClient().getZookeeperClient().getRetryPolicy());
    }

    @Override
    public void set(long value) throws CounterException {
        try {
            this.atomicLong.forceSet(value);
        } catch (Exception e) {
            throw new CounterException("set path ["+counter+"] error value is ["+value+"]",e);
        }
    }

    @Override
    public void increment(long increment) throws CounterException {
        try {
            this.atomicLong.add(increment);
        } catch (Exception e) {
            throw new CounterException("set path ["+counter+"] error value is ["+increment+"]",e);
        }
    }

    @Override
    public long get() throws CounterException {
        try {
            return this.atomicLong.get().postValue();
        } catch (Exception e) {
            throw new CounterException("get path ["+counter+"] error ",e);
        }
    }
}
