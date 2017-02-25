package com.young.example.spider.spider.persist;

import com.young.example.spider.config.BaseFactory;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class PersisterFactory {

    public synchronized static <Data,Meta> Persister<Data,Meta> getPersister(String classname) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return BaseFactory.getInstance(classname);
    }
}
