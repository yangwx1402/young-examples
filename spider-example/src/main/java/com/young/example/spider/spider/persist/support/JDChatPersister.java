package com.young.example.spider.spider.persist.support;

import com.young.example.spider.spider.entity.meta.JDSeedEntity;
import com.young.example.spider.spider.persist.Persister;
import com.young.example.spider.spider.entity.common.ParserEntity;
import com.young.example.spider.spider.persist.PersisterException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class JDChatPersister implements Persister<String,JDSeedEntity> {

    private static final String file_path = "D:\\data\\jd.json";

    @Override
    public void persist(ParserEntity<String> parserEntity, JDSeedEntity jdSeedEntity) throws PersisterException {
        try {
            FileUtils.writeStringToFile(new File(file_path),parserEntity.getData()+"\n","utf-8",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
