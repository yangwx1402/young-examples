package com.young.spider.counter;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by yangyong3 on 2017/1/13.
 */
public class CounterTool {
    private static AtomicLong weibo_search_counter = new AtomicLong(0);

    private static AtomicLong weibo_search_request_counter = new AtomicLong(0);

    private static AtomicLong weibo_topic_counter = new AtomicLong(0);

    private static AtomicLong weibo_topic_request_counter = new AtomicLong(0);

    public static void count(CounterType type, int increment) {
        if (CounterType.weibo_search_counter == type)
            weibo_search_counter.getAndAdd(increment);
        if (CounterType.weibo_search_request_counter == type)
            weibo_search_request_counter.getAndAdd(increment);
        if (CounterType.weibo_topic_counter == type)
            weibo_topic_counter.getAndAdd(increment);
        if (CounterType.weibo_topic_request_counter == type)
            weibo_topic_request_counter.getAndAdd(increment);
    }

    public static String print(){
        return "weibo_search_counter="+weibo_search_counter.get()+",weibo_search_request_counter="+weibo_search_request_counter.get()+",weibo_topic_counter="+weibo_topic_counter.get()+",weibo_topic_request_counter="+weibo_topic_request_counter.get();

    }
}
