package com.young.spring.rest.boot;

import com.young.spring.rest.config.BootConfig;
import com.young.spring.rest.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Created by yangyong3 on 2017/2/3.
 */
@SpringBootApplication
public class RestBootStrap {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BootConfig.class, args);
        System.out.println(context);
        StringRedisTemplate stringRedisTemplate = context.getBean(StringRedisTemplate.class);
        //stringRedisTemplate.opsForValue().set("ceshi","yangyong");
        RedisTemplate<String, User> userRedisTemplate = (RedisTemplate<String, User>) context.getBean("redisObject");
        //userRedisTemplate.opsForValue().set("ceshi",new User("yangyong",1));
        System.out.println(userRedisTemplate.opsForValue().get("ceshi").getUsername());
    }
}
