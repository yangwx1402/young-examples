package com.young.java.examples.druid;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * Created by dell on 2016/5/18.
 */
public class DruidDataSourceExample {
    public DataSource getDataSource(){
        DataSource dataSource = new DruidDataSource();

        return dataSource;
    }
}
