package com.young.java.examples.mq.activemq;

import com.young.java.examples.mq.AbstractMessageQueue;
import com.young.java.examples.mq.MessageQueueException;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public class ActivemqMessageQueue<T extends Serializable> extends AbstractMessageQueue<T> {

    private ConnectionFactory connectionFactory;

    private Connection connection;

    public ActivemqMessageQueue(String url, String queueName) {
        super(url, queueName);
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, url);
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            this.queueName = queueName;
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

    public ActivemqMessageQueue(String url, String queueName, int timeout) {
        this(url, queueName);
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
        }finally {
            try {
                session.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void send(Collection<T> list) throws MessageQueueException {
          for (T t:list){
              send(t);
          }
    }

    @Override
    public T receive() throws MessageQueueException {
        Session session = null;
        Destination destination = null;
        MessageConsumer consumer = null;
        ObjectMessage message = null;
        try{
            session = connection.createSession(Boolean.FALSE,Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(this.queueName);
            consumer = session.createConsumer(destination);
            message = (ObjectMessage) consumer.receive(timeout);
            return (T) message.getObject();
        }catch (Exception e){
           e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<T> receive(int size) throws MessageQueueException {
        List<T> list = new ArrayList<T>();
        for(int i=0;i<size;i++){
            list.add(receive());
        }
        return list;
    }
}
