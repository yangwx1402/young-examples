package com.young.java.examples.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import java.util.Properties;

/**
 * Created by yangyong3 on 2017/7/4.
 */
public class ActivemqConnection {

    private ConnectionFactory connectionFactory;

    private static final String ACTIVEMQ_URL_STRING = "activemq.url";

    private static final String DEFAULT_ACTIVEMQ_URL = "tcp://localhost:61616";

    public ActivemqConnection(Properties config){
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, config.getProperty(ACTIVEMQ_URL_STRING, DEFAULT_ACTIVEMQ_URL));
    }

    public Connection openConnection() throws JMSException {
        return connectionFactory.createConnection();
    }

    public void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
