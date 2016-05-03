package com.young.java.example.hbase;

import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

/**
 * Created by dell on 2016/4/22.
 */
public interface HTableCallBack {
    Object callback(Table table) throws Exception;
}
