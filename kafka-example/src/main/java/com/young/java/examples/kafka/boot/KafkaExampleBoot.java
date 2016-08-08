package com.young.java.examples.kafka.boot;

import com.young.java.examples.kafka.consumer.ConsumerThread;
import com.young.java.examples.kafka.consumer.support.StringMessageHandler;
import com.young.java.examples.kafka.producer.ProducerThread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Administrator on 2016/8/7.
 */
public class KafkaExampleBoot {
    public static void main(String[] args) throws IOException {
        List<String> messages = new ArrayList<>();
        for(int i=0;i<100;i++){
            messages.add("message_"+i);
        }
        String producerConfigPath = KafkaExampleBoot.class.getResource("/").getPath()+ File.separator+"producer.properties";
        String topic = "test";
        Properties producerConfig = new Properties();
        producerConfig.load(new FileInputStream(producerConfigPath));
        Thread producer = new Thread(new ProducerThread(topic,messages,producerConfig));

        String consumerConfigPath = KafkaExampleBoot.class.getResource("/").getPath()+ File.separator+"consumer.properties";
        Properties consumerConfig = new Properties();
        consumerConfig.load(new FileInputStream(consumerConfigPath));
        //
        consumerConfig.setProperty("auto.offset.reset","earliest");
        Thread consumer = new Thread(new ConsumerThread(topic,consumerConfig,1000,new StringMessageHandler()));

       // producer.start();
        consumer.start();

    }
}
