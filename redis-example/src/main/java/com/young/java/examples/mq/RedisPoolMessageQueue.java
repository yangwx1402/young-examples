package com.young.java.examples.mq;

import java.io.IOException;
import java.util.Date;

/**
 * Created by yangyong3 on 2016/12/19.
 */
public class RedisPoolMessageQueue extends RedisMessageQueue implements MessageQueue {

    public RedisPoolMessageQueue(RedisFactory redisFactory) {
        super(redisFactory);
    }

    public boolean pub(String queueName, Object obj, MessagePriority priority) throws IOException {
        return super.pub(queueName, obj, false);
    }

    public <T> T sub(String queueName, Class<T> tClass) throws IOException {
        return super.sub(queueName, tClass, false);
    }

    public long size(String queueName) throws IOException {
        return super.size(queueName, false);
    }

    public void clear(String queueName) throws IOException {
        super.clear(queueName, false);
    }

    public void destory() {
        super.destory();
        log.info("destory " + RedisPoolMessageQueue.class.getName() + " jedis resource " + new Date());
    }

}
