package com.young.java.examples.redis;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
/**
 * Created by dell on 2016/8/8.
 */
public class RedisTools {

    private RedisTemplate<Object,Object> redisMaster;

    private RedisTemplate<Object,Object> redisSlave;

    public enum Type{
        r,w;
    }

    public RedisTemplate<Object,Object> getTemplate(Type type){
        if(type==Type.w){
            return redisMaster;
        }else{
            return redisSlave;
        }
    }

    public RedisTemplate<Object,Object> getRedisMaster() {
        return redisMaster;
    }

    public void setRedisMaster(RedisTemplate<Object,Object> redisMaster) {
        this.redisMaster = redisMaster;
    }

    public RedisTemplate<Object,Object> getRedisSlave() {
        return redisSlave;
    }

    public void setRedisSlave(RedisTemplate<Object,Object> redisSlave) {
        this.redisSlave = redisSlave;
    }

    public boolean put(Object key,Object value){
        redisMaster.opsForValue().set(key, value);
        return true;
    }

    public boolean put(Object key,Object value,long timeout,TimeUnit unit){
        redisMaster.opsForValue().set(key, value, timeout, unit);
        return true;
    }

    public Object get(Object key){
        return redisSlave.opsForValue().get(key);
    }
}
