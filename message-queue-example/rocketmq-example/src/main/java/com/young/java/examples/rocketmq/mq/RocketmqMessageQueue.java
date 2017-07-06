package com.young.java.examples.rocketmq.mq;

import com.young.java.examples.mq.AbstractMessageQueue;
import com.young.java.examples.mq.MessageQueueException;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.Collection;
import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/5.
 */
public class RocketmqMessageQueue<T> extends AbstractMessageQueue<T> {

    private static final String ROCKETMQ_NAME_SERVER_STRING = "rocketmq.nameser";

    private static final String DEFAULT_ROCKETMQ_NAME_SERVER = "localhost:9876";

    private static final String ROCKETMQ_MESSAGE_GROUP_STRING = "rocketmq.mqgroup";

    private static final String DEFAULT_ROCKETMQ_MESSAGE_GROUP = "default_group";

    private final String rocket_message_group;

    private DefaultMQProducer producer;

    private DefaultMQPushConsumer consumer;

    public RocketmqMessageQueue(String queueName, Properties properties,boolean is_producer) {
        super(queueName, properties);
        rocket_message_group = properties.getProperty(ROCKETMQ_MESSAGE_GROUP_STRING, DEFAULT_ROCKETMQ_MESSAGE_GROUP);
        if(is_producer) {
            producer.setNamesrvAddr(properties.getProperty(ROCKETMQ_NAME_SERVER_STRING, DEFAULT_ROCKETMQ_NAME_SERVER));
            producer = new DefaultMQProducer(rocket_message_group);
        }else {
            consumer = new DefaultMQPushConsumer(rocket_message_group);
            consumer.setNamesrvAddr(properties.getProperty(ROCKETMQ_NAME_SERVER_STRING, DEFAULT_ROCKETMQ_NAME_SERVER));
        }
        try {
            if(is_producer) {
                producer.start();
            }else {
                consumer.start();
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(T t) throws MessageQueueException {
        try {
            producer.send(new Message(queueName, t.toString().getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageQueueException("send message error",e);
        }
    }

    @Override
    public void send(Collection<T> list) throws MessageQueueException {
         for(T t:list){
             send(t);
         }
    }

    @Override
    public T receive() throws MessageQueueException {
        return null;
    }

    @Override
    public Collection<T> receive(int size) throws MessageQueueException {
        return null;
    }

    @Override
    public void destory() throws MessageQueueException {
        if (producer != null) {
            producer.shutdown();
        }

    }

    @Override
    public int size() throws MessageQueueException {
        return 0;
    }
}
