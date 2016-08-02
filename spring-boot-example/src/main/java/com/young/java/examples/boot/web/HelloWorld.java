package com.young.java.examples.boot.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2016/8/2.
 */
@RestController
@EnableAutoConfiguration
public class HelloWorld {
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable String name){
        return "hello world "+name;
    }
    public static void main(String[] args){
        SpringApplication.run(HelloWorld.class,args);
    }
}
