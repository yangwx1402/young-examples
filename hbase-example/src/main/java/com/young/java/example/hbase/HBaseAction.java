package com.young.java.example.hbase;

import java.util.List;

/**
 * Created by dell on 2016/4/22.
 */
public interface HBaseAction {

    <T> T queryByRowKey(String tableName, byte[] rowKey, RowMapper<T> mapper) throws HBaseException;

    <T> List<T> queryByRange(String tableName,byte[] startRow,byte[] endRow,RowMapper<T> mapper) throws HBaseException;

    Object execute(String table,HTableCallBack callBack) throws HBaseException;

}
