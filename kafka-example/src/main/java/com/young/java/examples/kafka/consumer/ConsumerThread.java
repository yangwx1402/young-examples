package com.young.java.examples.kafka.consumer;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;


/**
 * Created by dell on 2016/8/8.
 */
public class ConsumerThread implements Runnable {

    private final Consumer<Integer, String> consumer;

    private final String topic;

    private Long batchSize;

    private ConsumerHandler<Integer,String,String> handler;

    public ConsumerThread(String topic,Properties properties,ConsumerHandler<Integer,String,String> handler){
        this(topic,properties,1000L,handler);
    }

    public ConsumerThread(String topic,Properties properties,long batchSize,ConsumerHandler<Integer,String,String> handler){
        this.topic = topic;
        this.batchSize = batchSize;
        consumer = new KafkaConsumer(properties);
        consumer.subscribe(Collections.singletonList(topic));
        this.handler = handler;
    }

    @Override
    public void run() {
        ConsumerRecords<Integer,String> records = consumer.poll(batchSize);
        System.out.println("fetch message size - "+records.count());
        for(ConsumerRecord<Integer,String> record:records){
            System.out.println(handler.handMessage(record));
        }
    }
}
