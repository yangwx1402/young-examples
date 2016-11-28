package com.young.java.examples.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by young.yang on 2016/11/12.
 */
public class BootExample {
    public static void main(String[] args){
        ApplicationContext context = new AnnotationConfigApplicationContext(BootConfig.class);
        Dao dao  = context.getBean(Dao.class);
        dao.add("yangyong","31");
        dao.delete(1);
    }
}
