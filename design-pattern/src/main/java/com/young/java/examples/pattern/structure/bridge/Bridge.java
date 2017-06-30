package com.young.java.examples.pattern.structure.bridge;

import java.sql.Connection;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public abstract class Bridge {

    private DriverManager driverManager;

    public Bridge(DriverManager driverManager){
        this.driverManager = driverManager;
    }

    public Connection getConnection(String url,String user,String password){
        return driverManager.getConnection(url,user,password);
    }
}
