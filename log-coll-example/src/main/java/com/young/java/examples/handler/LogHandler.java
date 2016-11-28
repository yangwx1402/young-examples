package com.young.java.examples.handler;

import com.young.java.examples.bean.LogMessageBean;
import com.young.java.examples.bean.LogOperBean;

/**
 * Created by young.yang on 2016/11/12.
 */
public interface LogHandler {
    LogMessageBean message(LogOperBean oper,Object[] args);
}
