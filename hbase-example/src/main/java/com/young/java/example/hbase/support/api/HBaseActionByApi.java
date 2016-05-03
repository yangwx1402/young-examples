package com.young.java.example.hbase.support.api;

import com.young.java.example.hbase.*;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Created by dell on 2016/4/22.
 */
public class HBaseActionByApi implements HBaseAction {

    private HBaseDataSource dataSource;

    public HBaseActionByApi(){
        dataSource = new HBaseDataSource();
    }

    public HBaseActionByApi(Properties properties){
        dataSource = new HBaseDataSource(properties);
    }

    @Override
    public <T> T queryByRowKey(String tableName, byte[] rowKey, RowMapper<T> mapper) throws HBaseException {
        return null;
    }

    @Override
    public <T> List<T> queryByRange(String tableName, byte[] startRow, byte[] endRow, RowMapper<T> mapper) throws HBaseException {
        return null;
    }

    @Override
    public Object execute(String tableName, HTableCallBack callBack) throws HBaseException {
        Table table = null;
        try {
           table = dataSource.getConnection().getTable(TableName.valueOf(tableName));
            return callBack.callback(table);
        } catch (Exception e) {
            e.printStackTrace();
            throw new HBaseOperException("执行execute报错",e);
        }
    }
}
