package com.young.spring.cloud.consumer;

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
/**
 * 採用Ribbon進行服務消費，Ribbon是負載均衡器，調用的時候需要使用RestTemplate對象進行，調用的時候還需要指定服務地址
 */
public class ConsumerBootStrapByRestTemplate {

    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args){
        ApplicationContext context = SpringApplication.run(ConsumerBootStrapByRestTemplate.class,args);
        RestTemplate restTemplate = context.getBean(RestTemplate.class);
        String serviceUrl = "http://compute-service/add?first=1&second=2";
        for(int i=0;i<10;i++) {
            Integer result = restTemplate.getForEntity(serviceUrl, Integer.class).getBody();
            System.out.println(result);
        }
    }
}
