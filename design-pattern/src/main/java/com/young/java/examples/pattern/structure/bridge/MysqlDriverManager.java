package com.young.java.examples.pattern.structure.bridge;

import java.sql.Connection;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public class MysqlDriverManager implements DriverManager{
    @Override
    public Connection getConnection(String url, String username, String password) {
        System.out.println("create mysql connection");
        return null;
    }
}
