package com.young.java.examples.activemq.mq;

import com.young.java.examples.activemq.ActivemqConnection;
import com.young.java.examples.mq.AbstractMessageQueue;
import com.young.java.examples.mq.MessageQueueException;

import javax.jms.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public class ActivemqMessageQueue<T extends Serializable> extends AbstractMessageQueue<T> {

    private ActivemqConnection activemqConnection;

    private Connection connection;

    private static final String ACTIVEMQ_TIMEOUT_STRING = "activemq.timeout";

    private static final String DEFAULT_ACTIVEMQ_TIMEOUT = "10000";

    private final int active_timeout;

    public ActivemqMessageQueue(String queueName, Properties config) {
        super(queueName, config);
        active_timeout = Integer.parseInt(this.config.getProperty(ACTIVEMQ_TIMEOUT_STRING, DEFAULT_ACTIVEMQ_TIMEOUT));
        try {
            activemqConnection = new ActivemqConnection(config);
            connection = activemqConnection.openConnection();
            connection.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void send(T t) throws MessageQueueException {
        Session session = null;
        Destination destination = null;
        MessageProducer producer = null;
        try {
            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(this.queueName);
            producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            producer.send(session.createObjectMessage(t));
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
            try {
                session.rollback();
            } catch (JMSException e1) {
                e1.printStackTrace();
            }
            throw new MessageQueueException("send message error ",e);
        } finally {
            try {
                session.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void send(Collection<T> list) throws MessageQueueException {
        for (T t : list) {
            send(t);
        }
    }

    @Override
    public T receive() throws MessageQueueException {
        Session session = null;
        Destination destination = null;
        MessageConsumer consumer = null;
        ObjectMessage message = null;
        try {
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(this.queueName);
            consumer = session.createConsumer(destination);
            message = (ObjectMessage) consumer.receive(active_timeout);
            //如果设置了客户端ack的话，这里就必须调用ack方法
//            message.acknowledge();
            return (T) message.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new MessageQueueException("receive message error ",e);
        }finally{
            if(session!=null){
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public Collection<T> receive(int size) throws MessageQueueException {
        List<T> list = new ArrayList<T>();
        for (int i = 0; i < size; i++) {
            list.add(receive());
        }
        return list;
    }

    @Override
    public void destory() throws MessageQueueException {
         activemqConnection.close(connection);
    }

    @Override
    public int size() throws MessageQueueException {
        return 0;
    }
}
