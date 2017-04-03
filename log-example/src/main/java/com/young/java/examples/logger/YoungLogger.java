package com.young.java.examples.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yangyong on 17-4-3.
 */
public class YoungLogger {

    public static final Logger getLogger(Class clazz){
       return LoggerFactory.getLogger(clazz);
    }

    public static final Logger getLogger(String name){
        return LoggerFactory.getLogger(name);
    }
}
