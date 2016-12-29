package com.young.java.examples.mq;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yangyong3 on 2016/12/23.
 */
public class RedisFactory {

    private static final Logger log = Logger.getLogger(RedisFactory.class);

    private GenericObjectPoolConfig config;

    private String ip = "localhost";

    private int port = 6379;

    private String auth;

    private ReentrantLock lockPool = new ReentrantLock();

    private ReentrantLock lockJedis = new ReentrantLock();

    private JedisPool jedisPool;

    private ThreadLocal<Jedis> jedisCache = new ThreadLocal<Jedis>();

    private static RedisFactory instance;

    private void init() {
        if (jedisPool != null)
            return;
        assert !lockPool.isHeldByCurrentThread();
        lockPool.lock();
        try {
            if (jedisPool == null) {
                config.setBlockWhenExhausted(false);
                jedisPool = new JedisPool(config, this.ip, this.port);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockPool.unlock();
        }
    }

    public Jedis getJedis() {
        Jedis jedis = jedisCache.get();
        if (jedis != null) {
            log.info("get jedis from threadLocal finished jedis object  is " + jedis);
            return jedis;
        }
        init();
        assert !lockJedis.isHeldByCurrentThread();
        lockJedis.lock();
        try {
            jedis = jedisPool.getResource();
            if (!StringUtils.isBlank(auth))
                jedis.auth(auth);
            log.info("get jedis from pool finished jedis object  is " + jedis);
            jedisCache.set(jedis);
            log.info("set jedis to threadLocal finished jedis object  is " + jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockJedis.unlock();
        }
        return jedis;
    }

    private RedisFactory(GenericObjectPoolConfig config, String ip, int port, String auth) {
        this.config = config;
        this.ip = ip;
        this.port = port;
        this.auth = auth;
    }
    public static RedisFactory getInstance(GenericObjectPoolConfig config, String ip, int port, String auth){
        if(instance == null)
            instance = new RedisFactory(config,ip,port,auth);
        return instance;
    }


    public void destory() {
        if (jedisPool != null)
            jedisPool.close();
        log.info("destory redis resource time is "+new Date());
    }
}
