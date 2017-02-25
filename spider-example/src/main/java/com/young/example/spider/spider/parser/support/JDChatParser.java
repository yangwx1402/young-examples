package com.young.example.spider.spider.parser.support;

import com.young.example.spider.spider.entity.common.CrawlerEntity;
import com.young.example.spider.spider.entity.common.PageEntity;
import com.young.example.spider.spider.entity.common.ParserEntity;
import com.young.example.spider.spider.entity.meta.JDSeedEntity;
import com.young.example.spider.spider.parser.Parser;
import com.young.example.spider.spider.parser.ParserException;
import com.young.example.spider.utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class JDChatParser implements Parser<String,JDSeedEntity> {
    @Override
    public ParserEntity<String> parser(CrawlerEntity<JDSeedEntity> crawlerEntity) throws ParserException {
        ParserEntity<String> parserEntity = new ParserEntity<String>();
        parserEntity.setData(crawlerEntity.getBody());
        PageEntity pageEntity = new PageEntity();
        try {
            Map<String,Object> map = JsonUtils.fromJson(crawlerEntity.getBody(),Map.class);
            pageEntity.setPage(crawlerEntity.getMeta().getPage());
            pageEntity.setTotalNum(Integer.parseInt(map.get("totalRecordNum").toString()));
            pageEntity.setTotalPage(Integer.parseInt(map.get("totalPage").toString()));
            parserEntity.setPage(pageEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parserEntity;
    }
}
