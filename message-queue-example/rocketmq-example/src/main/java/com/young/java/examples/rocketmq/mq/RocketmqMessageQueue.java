package com.young.java.examples.rocketmq.mq;

import com.young.java.examples.mq.MessageQueueException;
import com.young.java.examples.serializable.DSerializable;
import com.young.java.examples.serializable.java.JavaDSerialization;
import com.young.java.examples.topic.AbstractMessageTopic;
import com.young.java.examples.topic.TopicMessageCallback;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/5.
 */
public class RocketmqMessageQueue<T extends Serializable> extends AbstractMessageTopic<T> {

    private static final String ROCKETMQ_NAME_SERVER_STRING = "rocketmq.nameser";

    private static final String DEFAULT_ROCKETMQ_NAME_SERVER = "localhost:9876";

    private static final String ROCKETMQ_MESSAGE_GROUP_STRING = "rocketmq.mqgroup";

    private static final String DEFAULT_ROCKETMQ_MESSAGE_GROUP = "default_group";

    private final String rocket_message_group;

    private DefaultMQProducer producer;

    private DefaultMQPushConsumer consumer;

    private DSerializable<T, byte[]> dSerializable;

    public RocketmqMessageQueue(String queueName,Properties properties){
        this(queueName,properties,true);
    }

    public RocketmqMessageQueue(String queueName, Properties properties, boolean is_producer) {
        this(queueName, properties, is_producer, new JavaDSerialization<T>());

    }

    public RocketmqMessageQueue(String queueName, Properties properties, boolean is_producer, DSerializable<T, byte[]> dSerializable) {
        super(queueName, properties);
        rocket_message_group = properties.getProperty(ROCKETMQ_MESSAGE_GROUP_STRING, DEFAULT_ROCKETMQ_MESSAGE_GROUP);
        if (is_producer) {
            producer = new DefaultMQProducer(rocket_message_group);
            producer.setNamesrvAddr(properties.getProperty(ROCKETMQ_NAME_SERVER_STRING, DEFAULT_ROCKETMQ_NAME_SERVER));
        } else {
            consumer = new DefaultMQPushConsumer(rocket_message_group);
            consumer.setNamesrvAddr(properties.getProperty(ROCKETMQ_NAME_SERVER_STRING, DEFAULT_ROCKETMQ_NAME_SERVER));
        }
        this.dSerializable = dSerializable;
        try {
            if (is_producer) {
                producer.start();
            } else {
                consumer.subscribe(this.queueName, "*");
                //程序第一次启动从消息队列头获取数据
                consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
                //可以修改每次消费消息的数量，默认设置是每次消费一条
                consumer.setConsumeMessageBatchMaxSize(10);
            }
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(T t) throws MessageQueueException {
        try {
            producer.send(new Message(queueName, dSerializable.serialization(t)));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageQueueException("send message error", e);
        }
    }

    @Override
    public void send(Collection<T> list) throws MessageQueueException {
        for (T t : list) {
            send(t);
        }
    }

    @Override
    public void receive(final TopicMessageCallback<T> callback) throws MessageQueueException {
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                try {
                    for (MessageExt message : msgs) {
                        T t = dSerializable.deSerialization(message.getBody());
                        callback.process(t);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destory() throws MessageQueueException {
        if (producer != null) {
            producer.shutdown();
        }
        if (consumer != null) {
            consumer.shutdown();
        }
    }
}
