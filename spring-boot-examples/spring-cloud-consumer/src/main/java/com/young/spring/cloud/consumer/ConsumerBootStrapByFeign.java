package com.young.spring.cloud.consumer;

import com.young.spring.cloud.consumer.feignsupport.ComputerClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by young.yang on 2017/2/19.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan("com.young.spring.cloud.consumer.feignsupport")
/**
 * 採用feign進行服務消費，feign是聲明式接口編程，減少代碼量，而且不需要指定服務地址，只需要指定服務名稱即可
 */
public class ConsumerBootStrapByFeign {

    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(ConsumerBootStrapByFeign.class,args);
        ComputerClient client = context.getBean(ComputerClient.class);
        for(int i=0;i<10;i++)
        System.out.println(client.add(1,2));
    }
}
