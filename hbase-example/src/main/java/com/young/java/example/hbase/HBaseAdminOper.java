package com.young.java.example.hbase;

/**
 * Created by dell on 2016/4/22.
 */
public interface HBaseAdminOper {

    void createTable(String tableName,String[] columnFamily) throws HBaseException;

    void disableTable(String tableName) throws HBaseException;

    void dropTable(String tableName) throws HBaseException;

    void addColumnFamily(String tableName,String[] addColumnFamily) throws HBaseException;

    void removeColumnFamily(String tableName,String[] removeColumnFamily) throws HBaseException;
}
