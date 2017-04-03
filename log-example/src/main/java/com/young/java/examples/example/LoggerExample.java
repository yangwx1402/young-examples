package com.young.java.examples.example;

import com.young.java.examples.logger.YoungLogger;

/**
 * Created by yangyong on 17-4-3.
 */
public class LoggerExample {
    public static void main(String[] args){
        YoungLogger.getLogger(LoggerExample.class).info("haha 我来打下日志");
    }
}
