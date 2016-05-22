package com.young.java.example.hbase.support.api;

import com.google.common.collect.Lists;
import com.young.java.example.hbase.*;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * Created by dell on 2016/4/22.
 */
public class HBaseActionByApi implements HBaseAction {

    private HBaseDataSource dataSource;

    public HBaseActionByApi() {
        dataSource = new HBaseDataSource();
    }

    public HBaseActionByApi(Properties properties) {
        dataSource = new HBaseDataSource(properties);
    }

    private Table getHTable(String tableName) throws HBaseException, IOException {
        return dataSource.getConnection().getTable(TableName.valueOf(tableName));
    }

    @Override
    public <T> T queryByRowKey(String tableName, byte[] rowKey, RowMapper<T> mapper) throws HBaseException {
        Table table = null;
        Result result = null;
        try {
            table = getHTable(tableName);
            result = table.get(new Get(rowKey));
            return mapper.mapper(result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HBaseOperException("执行execute报错", e);
        }
    }

    @Override
    public <T> List<T> queryByRange(String tableName, byte[] startRow, byte[] endRow, RowMapper<T> mapper) throws HBaseException {
        Table table = null;
        Result result = null;
        Scan scan = null;
        ResultScanner resultScanner = null;
        Iterator<Result> it = null;
        List<T> ts = Lists.newArrayList();
        try {
            scan = new Scan();
            scan.setStartRow(startRow);
            scan.setStopRow(endRow);
            resultScanner = table.getScanner(scan);
            it = resultScanner.iterator();
            while(it.hasNext()){
               ts.add(mapper.mapper(it.next()));
            }
            return ts;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HBaseOperException("执行execute报错", e);
        }
    }

    @Override
    public <T> T execute(String tableName, HTableCallBack<T> callBack) throws HBaseException {
        Table table = null;
        try {
            table = dataSource.getConnection().getTable(TableName.valueOf(tableName));
            return callBack.callback(table);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HBaseOperException("执行execute报错", e);
        }
    }
}
