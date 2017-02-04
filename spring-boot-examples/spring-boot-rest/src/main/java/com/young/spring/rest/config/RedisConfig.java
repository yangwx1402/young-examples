package com.young.spring.rest.config;

import com.young.spring.rest.entity.User;
import com.young.spring.rest.redis.RedisObjectSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by yangyong3 on 2017/2/4.
 */
@Configuration
public class RedisConfig {
    @Bean(name="redisObject")
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, User> template = new RedisTemplate<String, User>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

}
