package com.young.spring.cloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yangyong3 on 2017/2/9.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerBootStrap {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(ConsumerBootStrap.class,args);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        String serviceUrl = "http://compute-service/add?first=1&second=2";
        Integer result = restTemplate.getForEntity(serviceUrl,Integer.class).getBody();
        System.out.println(result);
    }
}
