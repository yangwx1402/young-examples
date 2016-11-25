package com.young.java.examples.log4j2.appender.hbase;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Created by yangyong3 on 2016/11/25.
 */
public class DefaultHBasePutGenerator implements HBasePutGenerator {

    private static final byte[] OPER_CF = Bytes.toBytes("oper");

    private static final byte[] OPER_INFO = Bytes.toBytes("info");

    @Override
    public Put getPut(String string) {
        Put put = new Put(Bytes.toBytes(System.currentTimeMillis()));
        put.addColumn(OPER_CF, OPER_INFO, Bytes.toBytes(string));
        return put;
    }
}
