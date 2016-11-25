package com.young.java.examples.log4j2.appender.hbase;

import org.apache.hadoop.hbase.client.Put;

/**
 * Created by yangyong3 on 2016/11/25.
 */
public interface HBasePutGenerator {
    Put getPut(String string);
}
