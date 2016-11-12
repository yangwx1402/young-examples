package com.young.java.examples.bean;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by young.yang on 2016/11/12.
 */
@XStreamAlias("oper")
public class LogOperBean {
    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String methodsignature;
    private List<LogArgBean> args = Lists.newArrayList();

    private List<LogParamBean> params = Lists.newArrayList();

    public List<LogParamBean> getParams() {
        return params;
    }

    public void setParams(List<LogParamBean> params) {
        this.params = params;
    }

    public LogHandlerBean getHandler() {
        return handler;
    }

    public void setHandler(LogHandlerBean handler) {
        this.handler = handler;
    }

    private LogHandlerBean handler;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodsignature() {
        return methodsignature;
    }

    public void setMethodsignature(String methodsignature) {
        this.methodsignature = methodsignature;
    }

    public List<LogArgBean> getArgs() {
        return args;
    }

    public void setArgs(List<LogArgBean> args) {
        this.args = args;
    }
}
