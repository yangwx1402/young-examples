package com.young.java.examples.handler;

import com.young.java.examples.bean.LogMessageBean;
import com.young.java.examples.bean.LogStorageBean;
import com.young.java.examples.exception.LogStorageException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by young.yang on 2016/11/12.
 */
public class DefaultStorage implements LogStorage {

    private BlockingQueue<LogMessageBean> queue;

    private ExecutorService executor;

    private String file;

    @Override
    public void init(LogStorageBean storageBean) {
        queue =  new ArrayBlockingQueue<LogMessageBean>(10000);
        executor = Executors.newFixedThreadPool(10);
        file = storageBean.getParams().get(0).getValue();
    }

    @Override
    public void store(LogMessageBean messageBean) throws LogStorageException {
        StringBuilder sb = new StringBuilder(100);
        sb.append(messageBean.getStatus().toString()+"|"+messageBean.getCost()+"|");
        for(String line:messageBean.getOtherFiles()){
            sb.append(line+"|");
        }
        sb.append("\n");
        try {
            FileUtils.writeStringToFile(new File(file),sb.toString(),"utf-8",true);
        } catch (IOException e) {
            e.printStackTrace();
            throw new LogStorageException("日志写入失败",e);
        }
    }
}
