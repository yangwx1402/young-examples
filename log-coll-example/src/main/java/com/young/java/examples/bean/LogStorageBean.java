package com.young.java.examples.bean;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;

/**
 * Created by young.yang on 2016/11/12.
 */
@XStreamAlias("storage")
public class LogStorageBean {

    @XStreamAsAttribute
    private String type;
    @XStreamAsAttribute
    private String classname;

    private List<LogParamBean> params = Lists.newArrayList();

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public List<LogParamBean> getParams() {
        return params;
    }

    public void setParams(List<LogParamBean> params) {
        this.params = params;
    }
}
