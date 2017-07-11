package com.young.java.examples.asyn;

/**
 * Created by yangyong3 on 2017/7/11.
 * 回调接口
 */
public interface Callback {
    public void onSuccess(ExecuteResult result);

    public void onFailure(ExecuteResult result);

    public void onException(ExecuteResult result);
}
