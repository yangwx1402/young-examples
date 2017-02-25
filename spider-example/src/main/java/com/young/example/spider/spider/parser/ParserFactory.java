package com.young.example.spider.spider.parser;

import com.young.example.spider.config.BaseFactory;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class ParserFactory {
    public synchronized static <Data,Meta> Parser<Data,Meta> getParser(String classname) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return BaseFactory.getInstance(classname);
    }
}
