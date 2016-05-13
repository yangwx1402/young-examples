package com.young.java.examples.fileupload;

import java.io.Serializable;
import java.util.List;

/**
 * Created by issuser on 2016/5/12.
 */
public class RestResult<T> implements Serializable {
    private int statusCode;

    private String message;

    private T obj;

    private List<T> objs;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public List<T> getObjs() {
        return objs;
    }

    public void setObjs(List<T> objs) {
        this.objs = objs;
    }

    public RestResult(int statusCode,String message){
        this.statusCode = statusCode;
        this.message = message;
    }

    public RestResult(int statusCode,String message,T obj){
        this(statusCode,message);
        this.obj = obj;
    }
}
