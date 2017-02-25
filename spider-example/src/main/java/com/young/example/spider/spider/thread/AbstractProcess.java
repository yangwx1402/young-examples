package com.young.example.spider.spider.thread;

import com.young.example.spider.config.ConfigFactory;
import com.young.example.spider.distribution.mq.MessageQueue;
import com.young.example.spider.distribution.mq.MQFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by young.yang on 2017/2/25.
 */
public abstract class AbstractProcess<T> implements Runnable {

    private static final Logger log = LogManager.getLogger(AbstractProcess.class);

    private String seedQueueName;

    private String errorQueueName;

    protected MessageQueue<T> seedQueue;

    protected MessageQueue<T> errorQueue;

    private Class<T> tClass;

    private static final int retryNum = 3;

    public AbstractProcess(String seedQueueName, String errorQueueName,Class<T> tClass) {
        this.seedQueueName = seedQueueName;
        this.errorQueueName = errorQueueName;
        this.tClass = tClass;
    }

    public abstract void process(T t) throws Exception;

    public void run(){
        while(true) {
            try {
                if(seedQueue == null){
                    seedQueue = MQFactory.getMessageQueue(seedQueueName);
                }
                if(errorQueue == null){
                    errorQueue = MQFactory.getMessageQueue(errorQueueName);
                }
                T t = seedQueue.take(tClass);
                int count = 0;
                while(count<retryNum) {
                    try {
                        process(t);
                        log.info("crawler seed ["+t+"] ok");
                        break;
                    } catch (Exception e) {
                        log.error("crawler seed ["+t+"] error retry num is ["+count+"]");
                        ConfigFactory.sleepTime();
                        count++;
                        if(count == retryNum-1){
                            errorQueue.offer(t);
                        }
                    }
                }
                ConfigFactory.sleepTime();
            } catch (Exception e) {
                e.printStackTrace();
                ConfigFactory.sleepTime();
            }
        }
    }
}
