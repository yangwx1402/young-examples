package com.young.java.examples.kafka.boot;

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
        for(int i=0;i<5;i++){
            messages.add("message_"+i);
        }
        String configPath = KafkaExampleBoot.class.getResource("/").getPath()+ File.separator+"producer.properties";
        String topic = "test";
        Properties config = new Properties();
        config.load(new FileInputStream(configPath));
        Thread producer = new Thread(new ProducerThread(topic,messages,config));
        producer.start();
    }
}
