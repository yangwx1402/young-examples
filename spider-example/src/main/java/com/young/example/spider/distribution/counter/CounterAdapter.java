package com.young.example.spider.distribution.counter;

/**
 * Created by young.yang on 2017/2/25.
 */
public abstract class CounterAdapter implements Counter {
    protected String counter;

    public CounterAdapter(String counter) {
        this.counter = counter;
    }
}
