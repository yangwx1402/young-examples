package com.young.example.spider.spider.crawler;

import com.young.example.spider.config.support.SpiderConfigException;
import com.young.example.spider.spider.thread.jd.JDChatCrawlerThread;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class JDChatThreadTest {

    public static void main(String[] args) throws SpiderConfigException {
       for(int i=0;i<5;i++){
           new Thread(new JDChatCrawlerThread()).start();
       }
    }
}
