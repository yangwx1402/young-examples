package com.young.java.examples.activemq.transcation;

/**
 * Created by yangyong3 on 2017/7/4.
 */
public class DBOperator {
    public void openSession(){
        System.out.println("open Session");
    }

    public void commit(){
        System.out.println("Session commit");
    }

    public void rollback(){
        System.out.println("Session rollback");
    }

    public void operate(){
        System.out.println("operate");
    }
}
