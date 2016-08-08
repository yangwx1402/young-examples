package com.young.java.examples.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * Created by dell on 2016/8/8.
 */
public interface ConsumerHandler<KIN,VIN,RUSULT> {
    public RUSULT handMessage(ConsumerRecord<KIN,VIN> record);
}
