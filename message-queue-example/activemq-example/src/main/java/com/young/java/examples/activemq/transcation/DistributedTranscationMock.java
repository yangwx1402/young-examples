package com.young.java.examples.activemq.transcation;

import com.young.java.examples.activemq.ActivemqConnection;

import javax.jms.*;
import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/4.
 */
public class DistributedTranscationMock {
    private ActivemqConnection activemqConnection;

    private Connection connection;

    public DistributedTranscationMock(Properties config) {
        try {
            activemqConnection = new ActivemqConnection(config);
            connection = activemqConnection.openConnection();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void distributedTranscation() throws Exception {
        DBOperator dbOperator = new DBOperator();
        Session session = null;
        String queueName = "distributed";
        Destination destination = null;
        MessageProducer producer = null;
        session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
        destination = session.createQueue(queueName);
        producer = session.createProducer(destination);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        try {
            //1.先发送消息到mq中，这个消息是预提交的消息,如果这里就报错，那么DB的操作不会进行，页面上也是直接返回404等等提示信息。
            producer.send(session.createTextMessage("precommit"));
        }catch (JMSException e){
            throw e;
        }
        try {
            //2.然后执行db操作,如果执行DB报错了，那么就回滚消息,还是保证一致性
            dbOperator.openSession();
            dbOperator.operate();
        }catch (Exception e){
            dbOperator.rollback();
            session.rollback();
            throw new Exception(e);
        }
        //3都没有报错，说明一切正常，开始提交,如果提交失败，那么再一起回滚
        try{
            dbOperator.commit();
        }catch (Exception e){
            e.printStackTrace();
            dbOperator.rollback();
            session.rollback();
            throw new Exception(e);
        }
        try {
            session.commit();
        }catch (Exception e){
            //重发
        }
        connection.close();
    }
   public static void main(String[] args) throws Exception {
       Properties properties = new Properties();
       properties.load(DistributedTranscationMock.class.getResourceAsStream("/activemq.properties"));
       DistributedTranscationMock mock = new DistributedTranscationMock(properties);
       mock.distributedTranscation();
   }
}
