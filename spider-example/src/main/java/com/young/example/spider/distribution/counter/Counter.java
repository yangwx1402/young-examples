package com.young.example.spider.distribution.counter;

/**
 * Created by young.yang on 2017/2/25.
 */
public interface Counter {
    public void set(long value) throws CounterException;

    public void increment(long increment) throws CounterException;

    public long get() throws CounterException;
}
