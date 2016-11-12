package com.young.java.examples.handler;

import com.google.common.collect.Lists;
import com.young.java.examples.bean.LogArgBean;
import com.young.java.examples.bean.LogMessageBean;
import com.young.java.examples.bean.LogOperBean;

import java.util.Collections;
import java.util.List;

/**
 * Created by young.yang on 2016/11/12.
 */
public class DefaultLogHandler implements LogHandler {
    @Override
    public LogMessageBean message(LogOperBean oper, Object[] args) {
        LogMessageBean messageBean = new LogMessageBean();
        List<String> lists = Lists.newArrayList();
        List<LogArgBean> argsConfig = oper.getArgs();
        if(!argsConfig.isEmpty()) {
            Collections.sort(argsConfig);
            for(int i=0;i<args.length;i++){
                lists.add(argsConfig.get(i).getName()+"="+args[i]+"|");
            }
        }
        messageBean.setOtherFiles(lists);
        return messageBean;
    }
}
