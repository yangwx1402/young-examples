package com.young.java.examples.kafka.consumer.support;

import com.young.java.examples.kafka.consumer.ConsumerHandler;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * Created by dell on 2016/8/8.
 */
public class StringMessageHandler implements ConsumerHandler<Integer,String,String> {
    @Override
    public String handMessage(ConsumerRecord<Integer, String> record) {
        return record.topic()+":"+record.key()+":"+record.value()+":"+record.offset();
    }
}
