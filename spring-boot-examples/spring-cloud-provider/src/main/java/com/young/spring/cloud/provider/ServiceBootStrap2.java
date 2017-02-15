package com.young.spring.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by yangyong3 on 2017/2/9.
 */
@SpringBootApplication
@EnableEurekaClient
public class ServiceBootStrap2 {
    public static void main(String[] args){
        SpringApplication.run(ServiceBootStrap2.class,args);
    }
}