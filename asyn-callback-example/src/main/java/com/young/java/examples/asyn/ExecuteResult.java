package com.young.java.examples.asyn;

/**
 * Created by yangyong3 on 2017/7/11.
 * 异步执行结果对象，可以根据该抽象类进行继承扩展
 */
public abstract class ExecuteResult {
    public static enum ExecuteStatus{
        SUCCESS,FAILURE,EXCEPTION;
    }
    private ExecuteStatus status;

    public ExecuteResult(ExecuteStatus status) {
        this.status = status;
    }

    public ExecuteStatus getStatus() {
        return status;
    }

    public void setStatus(ExecuteStatus status) {
        this.status = status;
    }
}
