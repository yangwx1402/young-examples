package com.young.java.example.hbase;

/**
 * Created by dell on 2016/4/22.
 */
public class HBaseOperException extends HBaseException {
    public HBaseOperException(String message,Throwable e){
        super(message,e);
    }
}
