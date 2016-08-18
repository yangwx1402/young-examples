package com.young.java.examples.boot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

/**
 * Created by dell on 2016/8/17.
 */
@SpringBootApplication
public class ApplicationBoot {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ApplicationBoot.class, args);

        System.out.println("Let's inspect the beans provided by Spring Boot:");

        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }
}
