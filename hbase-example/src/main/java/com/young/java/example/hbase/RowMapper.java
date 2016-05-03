package com.young.java.example.hbase;

import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Scan;

import java.util.List;

/**
 * Created by dell on 2016/4/22.
 */
public interface RowMapper<T> {

    public T mapper(Get get);

    public List<T> mapper(Scan scan);
}
