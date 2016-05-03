package com.young.java.example.hbase.support.api;

import com.young.java.example.hbase.HBaseAdminException;
import com.young.java.example.hbase.HBaseAdminOper;
import com.young.java.example.hbase.HBaseException;
import com.young.java.example.hbase.util.ArrayUtils;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by dell on 2016/4/22.
 */
public class HBaseAdminByApi implements HBaseAdminOper {

    private HBaseDataSource dataSource;

    public HBaseAdminByApi() {
        dataSource = new HBaseDataSource();
    }

    public HBaseAdminByApi(Properties configs) {
        dataSource = new HBaseDataSource(configs);
    }

    @Override
    public void createTable(String tableName, String[] columnFamily) throws HBaseException {
        Admin admin = null;
        HTableDescriptor tableDescriptor = null;
        try {
            admin = dataSource.getConnection().getAdmin();
            tableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
            if (columnFamily != null || columnFamily.length > 0) {
                for (String column : columnFamily) {
                    tableDescriptor.addFamily(new HColumnDescriptor(column));
                }
            }
            admin.createTable(tableDescriptor);
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new HBaseAdminException("创建表失败,tableName=" + tableName + ",columnFamily=" + ArrayUtils.mkString(columnFamily, ","), e);
        }
    }

    @Override
    public void disableTable(String tableName) throws HBaseException {
        Admin admin = null;
        try {
            admin = dataSource.getConnection().getAdmin();
            admin.disableTable(TableName.valueOf(tableName));
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new HBaseAdminException("disable 表失败,tableName=" + tableName, e);
        }
    }

    @Override
    public void dropTable(String tableName) throws HBaseException {
        Admin admin = null;
        try {
            admin = dataSource.getConnection().getAdmin();
            if (!admin.isTableDisabled(TableName.valueOf(tableName))) {
                disableTable(tableName);
            }
            admin.deleteTable(TableName.valueOf(tableName));
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new HBaseAdminException("delete 表失败,tableName=" + tableName, e);
        }
    }

    @Override
    public void addColumnFamily(String tableName, String[] addColumnFamily) throws HBaseException {
        Admin admin = null;
        try {
            admin = dataSource.getConnection().getAdmin();
            if (!admin.isTableDisabled(TableName.valueOf(tableName))) {
                disableTable(tableName);
            }
            if (addColumnFamily != null || addColumnFamily.length > 0) {
                for (String column : addColumnFamily)
                    admin.addColumn(TableName.valueOf(tableName), new HColumnDescriptor(column));
            }
            admin.enableTable(TableName.valueOf(tableName));
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new HBaseAdminException("add column 失败,tableName=" + tableName + ",addColumnFamily=" + ArrayUtils.mkString(addColumnFamily, ","), e);
        }
    }

    @Override
    public void removeColumnFamily(String tableName, String[] removeColumnFamily) throws HBaseException {
        Admin admin = null;
        try {
            admin = dataSource.getConnection().getAdmin();
            if (!admin.isTableDisabled(TableName.valueOf(tableName))) {
                disableTable(tableName);
            }
            if (removeColumnFamily != null || removeColumnFamily.length > 0) {
                for (String column : removeColumnFamily)
                    admin.deleteColumn(TableName.valueOf(tableName), Bytes.toBytes(column));
            }
            admin.enableTable(TableName.valueOf(tableName));
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new HBaseAdminException("remove column 失败,tableName=" + tableName + ",removeColumnFamily=" + ArrayUtils.mkString(removeColumnFamily, ","), e);
        }
    }
}
