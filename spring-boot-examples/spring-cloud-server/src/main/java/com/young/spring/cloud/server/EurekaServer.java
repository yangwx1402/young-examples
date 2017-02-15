package com.young.spring.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by yangyong3 on 2017/2/8.
 * 启动Eureka服务端,也就是服务注册管理中心
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
    public static void main(String[] args){
        SpringApplication.run(EurekaServer.class,args);
    }
}
