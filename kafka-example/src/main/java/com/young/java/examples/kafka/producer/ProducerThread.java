package com.young.java.examples.kafka.producer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/8/7.
 */
public class ProducerThread implements Runnable{

    private Producer<Integer,String> producer;

    private String topic;

    private List<String> messages;

    private Properties config;

    public ProducerThread(String topic,List<String> messages,Properties config){
        this.topic = topic;
        this.messages = messages;
        this.config = config;
    }

    public Producer getProducer(){
        if(producer == null)
            producer = new KafkaProducer(config);
        System.out.println("get producer "+producer);
        return producer;
    }

    @Override
    public void run() {
        if(!CollectionUtils.isEmpty(messages)) {
            ProducerRecord record = null;
            System.out.println("send messages");
            for (int i=0;i<messages.size();i++) {
                System.out.println("send messages - "+i);
                record = new ProducerRecord(topic,i, messages.get(i));
                System.out.println(record);
                try {
                    System.out.println("send result -"+getProducer().send(record).get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println("send - "+i);
            }
        }
        producer.close(1, TimeUnit.SECONDS);
    }
}
