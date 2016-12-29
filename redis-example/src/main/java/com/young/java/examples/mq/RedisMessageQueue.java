package com.young.java.examples.mq;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.io.IOException;

/**
 * Created by yangyong3 on 2016/11/29.
 */
public abstract class RedisMessageQueue implements MessageQueue {

    protected static final Logger log = Logger.getLogger(RedisMessageQueue.class);

    protected static final String SPIDER_REDIS_QUEUE_PREFIX = "SPIDER_SF.";

    protected SpiderMessagePool queueConfig;

    private int capacity;

    private RedisFactory redisFactory;

    public RedisMessageQueue(RedisFactory redisFactory){
        this.redisFactory = redisFactory;
    }

    public void setQueueConfig(SpiderMessagePool messagePool) {
        this.queueConfig = messagePool;
        this.capacity = queueConfig.getPoolSize() == null ? 1000 : queueConfig.getPoolSize();
    }

    public boolean pub(String queueName, Object obj, boolean close) throws IOException {
        if (size(queueName) >= capacity()) {
            return false;
        }
        Jedis jedis = redisFactory.getJedis();
        if (jedis == null)
            return false;
        try {
            jedis.lpush(SPIDER_REDIS_QUEUE_PREFIX + queueName, JsonUtils.toJson(obj));
            log.info("pub now " + SPIDER_REDIS_QUEUE_PREFIX + queueName + " message pool is -" + size(queueName));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null && close) {
                jedis.close();
            }
        }
        return false;
    }

    public <T> T sub(String queueName, Class<T> tClass, boolean close) throws IOException {
        Jedis jedis = redisFactory.getJedis();
        if (jedis == null)
            return null;
        try {
            String value = jedis.rpop(SPIDER_REDIS_QUEUE_PREFIX + queueName);
            if (!StringUtils.isEmpty(value)) {
                log.info("sub now " + SPIDER_REDIS_QUEUE_PREFIX + queueName + " message pool is -" + size(queueName));
                return JsonUtils.fromJson(value, tClass);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null && close)
                jedis.close();
        }
        return null;
    }

    public long size(String queueName, boolean close) throws IOException {
        Jedis jedis = redisFactory.getJedis();
        if (jedis == null)
            return Integer.MAX_VALUE;
        try {
            return jedis.llen(SPIDER_REDIS_QUEUE_PREFIX + queueName);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null && close)
                jedis.close();
        }
        return 0;
    }

    public void clear(String queueName, boolean close) throws IOException {
        Jedis jedis = redisFactory.getJedis();
        if (jedis == null)
            return;
        try {
            jedis.del(SPIDER_REDIS_QUEUE_PREFIX + queueName);
            log.info("clear " + queueName + " now size is " + size(SPIDER_REDIS_QUEUE_PREFIX + queueName));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null && close)
                jedis.close();
        }
    }

    public void destory() {
        if (redisFactory != null)
            redisFactory.destory();
    }

    public int capacity() {
        return capacity;
    }
}
