package com.young.java.examples.asyn;

/**
 * Created by yangyong3 on 2017/7/11.
 * 业务执行接口
 */
public interface Executable {
    /**
     * 负责真实业务逻辑的执行
     * @throws Exception
     */
    public ExecuteResult execute() throws Exception;
}
