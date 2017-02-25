package com.young.example.spider.spider.crawler.common;

import com.young.example.spider.http.Response;
import com.young.example.spider.spider.entity.common.CrawlerEntity;
import com.young.example.spider.http.HttpWalker;
import com.young.example.spider.http.Request;
import com.young.example.spider.http.support.HttpWalkerFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by yangyong3 on 2017/2/23.
 * 抓取适配器
 */
public abstract class CrawlerAdapter<Meta> implements Crawler<Meta> {

    private static final Logger log = LogManager.getLogger(CrawlerAdapter.class);

    /**
     * 根据Meta信息组装Request对象
     * @param meta
     * @return
     */
    public abstract Request createRuquest(Meta meta) throws CrawlerException;
    /**
     * 检查Response的有效性
     * @param request
     * @param response
     * @param meta
     * @return
     * @throws CrawlerException
     */
    public abstract boolean check(Request request, Response response, Meta meta) throws CrawlerException;

    @Override
    public CrawlerEntity crawlByGet(Meta meta) throws CrawlerException {
        Request request = createRuquest(meta);
        HttpWalker walker = HttpWalkerFactory.getHttpWalker();
        Response response = null;
        try {
            long start = System.currentTimeMillis();
            log.info("get request -["+request+"]");
            response = walker.get(request);
            log.info("get request -["+request+"] ok cost time -[ "+(System.currentTimeMillis()-start)+" ]response is ["+response+"]");
            return processResponse(request, response, meta);
        } catch (IOException e) {
            throw new CrawlerException("get request -[" + request + "] error response is [" + response + "]", e);
        }
    }

    private CrawlerEntity processResponse(Request request, Response response, Meta meta) throws CrawlerException {
        check(request, response, meta);
        CrawlerEntity entity = new CrawlerEntity();
        entity.setBody(response.getContent());
        entity.setMeta(meta);
        return entity;
    }

    @Override
    public CrawlerEntity crawlByPost(Meta meta) throws CrawlerException {
        Request request = createRuquest(meta);
        HttpWalker walker = HttpWalkerFactory.getHttpWalker();
        Response response = null;
        try {
            long start = System.currentTimeMillis();
            log.info("get request -["+request+"]");
            response = walker.post(request);
            log.info("get request -["+request+"] ok cost time -[ "+(System.currentTimeMillis()-start)+" ]response is ["+response+"]");
            return processResponse(request, response, meta);
        } catch (IOException e) {
            throw new CrawlerException("get request -[" + request + "] error response is [" + response + "]", e);
        }
    }
}
