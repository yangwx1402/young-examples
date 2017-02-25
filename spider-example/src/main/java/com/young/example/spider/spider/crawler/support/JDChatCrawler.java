package com.young.example.spider.spider.crawler.support;

import com.young.example.spider.http.cookie.SpiderCookieFactory;
import com.young.example.spider.spider.crawler.common.CrawlerException;
import com.young.example.spider.spider.crawler.common.CrawlerAdapter;
import com.young.example.spider.spider.crawler.common.CrawlerType;
import com.young.example.spider.spider.entity.meta.JDSeedEntity;
import com.young.example.spider.http.Request;
import com.young.example.spider.http.Response;
import com.young.example.spider.http.cookie.CookieException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class JDChatCrawler extends CrawlerAdapter<JDSeedEntity> {

    private static final Logger log = LogManager.getLogger(JDChatCrawler.class);

    private static final String baseUrl = "http://kf.jd.com/chatLog/queryList.action?";

    @Override
    public Request createRuquest(JDSeedEntity jdSeedEntity) throws CrawlerException {
        String url = getUrl(jdSeedEntity);
        String cookie = null;
        try {
            cookie = SpiderCookieFactory.getCookie(CrawlerType.JD);
            if(StringUtils.isBlank(cookie))
                throw new CrawlerException("cookie is null cookie is ["+cookie+"]");
        }  catch (CookieException e) {
            throw new CrawlerException("cookie error cookie is ["+cookie+"]",e);
        }
        Map<String, String> header = new HashMap<String, String>();
        header.put("Cookie", cookie);
        return new Request(url, header);
    }

    @Override
    public boolean check(Request request, Response response, JDSeedEntity jdSeedEntity) throws CrawlerException {
        //这里可以对response进行判断，比如statusCode！=200或者其他错误
        if(response.getContent().trim().startsWith("<!DOCTYPE html>")) {
            try {
                SpiderCookieFactory.delCookie(CrawlerType.JD);
            } catch (CookieException e) {
                throw new CrawlerException("delete cookie error",e);
            }
            throw new CrawlerException("Cookie expire", new CookieException("cookie expired"));
        }
        return (response.getStatusCode() == 200);
    }

    private String getUrl(JDSeedEntity jdSeedEntity) {
        StringBuilder url = new StringBuilder(100);
        url.append(baseUrl);
        url.append("page=" + jdSeedEntity.getPage() + "&");
        url.append("pageSize=" + jdSeedEntity.getPageSize() + "&");
        url.append("startTime=" + jdSeedEntity.getStartTime() + "&");
        url.append("endTime=" + jdSeedEntity.getEndTime() + "&");
        url.append("productId=" + jdSeedEntity.getProductId() + "&");
        url.append("orderId=" + jdSeedEntity.getOrderId() + "&");
        url.append("customer=" + jdSeedEntity.getCustomer() + "&");
        url.append("servicePin=" + jdSeedEntity.getServicePin() + "&");
        url.append("sessionType=" + jdSeedEntity.getSessionType() + "&");
        url.append("sessionStatus=" + jdSeedEntity.getSessionStatus() + "&");
        url.append("keyWord=" + jdSeedEntity.getKeyWord());
        log.info("get url ok url is -["+url.toString()+"]");
        return url.toString();
    }
}
