package com.young.java.examples.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by young.yang on 2016/11/10.
 */
public class LogExample {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(LogConfig.class);
        Bussiness bussiness = context.getBean(Bussiness.class);
        bussiness.run();
    }
}
