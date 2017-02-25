package com.young.example.spider.spider.thread.jd;

import com.young.example.spider.config.ConfigFactory;
import com.young.example.spider.distribution.mq.MessageQueue;
import com.young.example.spider.spider.crawler.common.CrawlerFactory;
import com.young.example.spider.spider.entity.common.CrawlerEntity;
import com.young.example.spider.spider.parser.ParserFactory;
import com.young.example.spider.spider.persist.Persister;
import com.young.example.spider.spider.thread.AbstractProcess;
import com.young.example.spider.spider.crawler.common.Crawler;
import com.young.example.spider.spider.entity.common.ParserEntity;
import com.young.example.spider.config.support.SpiderConfigException;
import com.young.example.spider.spider.entity.meta.JDSeedEntity;
import com.young.example.spider.spider.parser.Parser;
import com.young.example.spider.spider.persist.PersisterFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class JDChatCrawlerThread extends AbstractProcess<JDSeedEntity> {

    private static final Logger log = LogManager.getLogger(JDChatCrawlerThread.class);

    public JDChatCrawlerThread(String seedQueueName, String errorQueueName, Class<JDSeedEntity> jdSeedEntityClass, MessageQueue<JDSeedEntity> queue) {
        super(seedQueueName, errorQueueName, jdSeedEntityClass);
    }

    public JDChatCrawlerThread() throws SpiderConfigException {
        super(ConfigFactory.getConfig().getMessageQueue().getQueueNamePrefix()+ConfigFactory.getProperty("spider.crawler.queue.seed.jd"),ConfigFactory.getConfig().getMessageQueue().getQueueNamePrefix()+ConfigFactory.getProperty("spider.crawler.queue.error.jd"),JDSeedEntity.class);
    }

    public void process(JDSeedEntity seedEntity) throws Exception {
        Crawler<JDSeedEntity> crawler = CrawlerFactory.getCrawler(ConfigFactory.getProperty("spider.crawler.chat.jd.classname"));
        Parser<String, JDSeedEntity> parser = ParserFactory.getParser(ConfigFactory.getProperty("spider.parser.chat.jd.classname"));
        Persister<String, JDSeedEntity> persister = PersisterFactory.getPersister(ConfigFactory.getProperty("spider.persist.chat.jd.classname"));
        CrawlerEntity<JDSeedEntity> crawlerEntity = null;
        ParserEntity<String> parserEntity = null;
        if (seedEntity != null) {
            crawlerEntity = crawler.crawlByGet(seedEntity);
            parserEntity = parser.parser(crawlerEntity);
            persister.persist(parserEntity, seedEntity);
        }
    }
}
