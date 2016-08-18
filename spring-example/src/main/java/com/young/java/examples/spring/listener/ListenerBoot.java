package com.young.java.examples.spring.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by dell on 2016/8/18.
 */
public class ListenerBoot {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ListenerConfig.class);
        BookingService bookingService = applicationContext.getBean(BookingService.class);
        bookingService.booking("123","yongyanga@isoftstone.com");
    }
}
