package com.young.example.spider.spider.task;

import com.young.example.spider.config.ConfigFactory;
import com.young.example.spider.distribution.mq.MQException;
import com.young.example.spider.spider.crawler.common.CrawlerException;
import com.young.example.spider.spider.parser.support.JDChatParser;
import com.young.example.spider.distribution.task.DistributedTaskException;
import com.young.example.spider.distribution.task.leadertask.LeaderTask;
import com.young.example.spider.distribution.task.leadertask.ZKDistributedLeaderTaskAdapter;
import com.young.example.spider.spider.crawler.common.Crawler;
import com.young.example.spider.spider.crawler.support.JDChatCrawler;
import com.young.example.spider.spider.entity.common.CrawlerEntity;
import com.young.example.spider.spider.entity.common.ParserEntity;
import com.young.example.spider.config.support.SpiderConfigException;
import com.young.example.spider.spider.entity.meta.JDSeedEntity;
import com.young.example.spider.distribution.mq.MQFactory;
import com.young.example.spider.distribution.mq.MessageQueue;
import com.young.example.spider.spider.parser.Parser;
import com.young.example.spider.spider.parser.ParserException;
import com.young.example.spider.spider.persist.PersisterException;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yangyong3 on 2017/2/23.
 * 按照分页将京东前一天的所有聊天记录拆分作为种子发送到分布式消息队列中
 */
public class JDCrawlerSeedTask extends ZKDistributedLeaderTaskAdapter {

    private static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    private static final long one_day = 1000 * 60 * 60 * 24 * 1;

    private String queueName;

    private MessageQueue<JDSeedEntity> queue;

    public JDCrawlerSeedTask() {
        try {
            this.queueName = ConfigFactory.getConfig().getMessageQueue().getQueueNamePrefix() + ConfigFactory.getProperty("spider.crawler.queue.seed.jd");
            this.queue = MQFactory.getMessageQueue(this.queueName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SpiderConfigException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Object task() throws DistributedTaskException, ParserException, MQException {
        String beforeDay = format.format(new Date(System.currentTimeMillis() - one_day));
        JDSeedEntity seedEntity = new JDSeedEntity(1, 100, beforeDay, beforeDay);
        Crawler<JDSeedEntity> crawler = new JDChatCrawler();
        Parser<String, JDSeedEntity> parser = new JDChatParser();
        CrawlerEntity<JDSeedEntity> crawlerEntity = null;
        int count = 0;
        while (count < 3) {
            try {
                crawlerEntity = crawler.crawlByGet(seedEntity);
                break;
            } catch (Exception e) {
                count++;
                System.out.println("第"+count+"次抓取失败");
                e.printStackTrace();
                ConfigFactory.sleepTime();
            }
        }
        ParserEntity<String> parserEntity = parser.parser(crawlerEntity);
            for (int i = 1; i <= parserEntity.getPage().getTotalPage(); i++) {
                seedEntity.setPage(i);
                queue.offer(seedEntity);
            }
        return null;
    }

    @Override
    protected Object change() {
        return null;
    }

    public static void main(String[] args) throws CrawlerException, ParserException, PersisterException, InterruptedException, SpiderConfigException, MQException, DistributedTaskException {
        LeaderTask task = new JDCrawlerSeedTask();
        task.runTask();
    }
}
