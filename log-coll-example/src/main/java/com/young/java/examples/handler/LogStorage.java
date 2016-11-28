package com.young.java.examples.handler;

import com.young.java.examples.bean.LogMessageBean;
import com.young.java.examples.bean.LogStorageBean;
import com.young.java.examples.exception.LogStorageException;

/**
 * Created by young.yang on 2016/11/12.
 */
public interface LogStorage {

    void init(LogStorageBean storageBean);

    void store(LogMessageBean messageBean) throws LogStorageException;
}
