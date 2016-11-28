package com.young.java.examples.log4j2.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by yangyong3 on 2016/11/25.
 */
public class Log4j2Example {
    private static final Logger log = LogManager.getLogger(Log4j2Example.class);

    public void example() {
        log.info("test");
        System.out.println("business");
    }

    public static void main(String[] args) {
        Log4j2Example example = new Log4j2Example();
        example.example();
    }
}
