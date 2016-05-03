package com.young.java.example.hbase;

import org.apache.hadoop.hbase.client.Result;

/**
 * Created by dell on 2016/4/22.
 */
public interface RowMapper<T> {

    public T mapper(Result rs);
}
