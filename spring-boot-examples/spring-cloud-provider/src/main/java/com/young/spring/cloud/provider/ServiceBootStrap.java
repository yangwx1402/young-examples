package com.young.spring.cloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by yangyong3 on 2017/2/9.
 * 启动服务提供者
 */
@SpringBootApplication
@EnableEurekaClient
public class ServiceBootStrap {
    public static void main(String[] args){
        SpringApplication.run(ServiceBootStrap.class,args);
    }
}
