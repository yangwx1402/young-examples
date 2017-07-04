package com.young.java.examples.activemq.topic;

import com.young.java.examples.activemq.ActivemqConnection;
import com.young.java.examples.mq.MessageQueueException;
import com.young.java.examples.topic.AbstractMessageTopic;
import com.young.java.examples.topic.TopicMessageCallback;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/4.
 */
public class ActivemqMessageTopic<T extends Serializable> extends AbstractMessageTopic<T> {

    private ActivemqConnection activemqConnection;

    private Connection connection;

    private static final String ACTIVEMQ_TIMEOUT_STRING = "activemq.timeout";

    private static final String DEFAULT_ACTIVEMQ_TIMEOUT = "10000";

    private final int active_timeout;

    public ActivemqMessageTopic(String queueName, Properties config) {
        super(queueName, config);
        active_timeout = Integer.parseInt(this.config.getProperty(ACTIVEMQ_TIMEOUT_STRING, DEFAULT_ACTIVEMQ_TIMEOUT));
        try {
            activemqConnection = new ActivemqConnection(config);
            connection = activemqConnection.openConnection();
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(T message) throws MessageQueueException {
        Session session = null;
        Destination destination = null;
        MessageProducer producer = null;
        try {
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createTopic(this.queueName);
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            producer.send(session.createObjectMessage(message));
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
            try {
                session.rollback();
            } catch (JMSException e1) {
                e1.printStackTrace();
            }
            throw new MessageQueueException("send message error ", e);
        } finally {
            try {
                session.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void send(Collection<T> messages) throws MessageQueueException {
        for (T message : messages) {
            send(message);
        }
    }

    @Override
    public void receive(final TopicMessageCallback<T> callback) throws MessageQueueException {
        Session session = null;
        Topic topic = null;
        MessageConsumer consumer = null;
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            topic = session.createTopic(this.queueName);
            consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    ObjectMessage objectMessage = (ObjectMessage) message;
                    try {
                        T t = (T) objectMessage.getObject();
                        callback.process(t);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (JMSException e) {
            e.printStackTrace();
            throw new MessageQueueException("receive message error ", e);
        } finally {

        }
    }

    @Override
    public void destory() throws MessageQueueException {
        if (this.connection != null) {
            try {
                this.connection.close();
            } catch (JMSException e) {
                throw new MessageQueueException("destory connection error message is ", e);
            }
        }
    }
}
