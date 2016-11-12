package com.young.java.examples.bean;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by young.yang on 2016/11/12.
 */
@XStreamAlias("module")
public class LogModuleBean {

    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String split;
    @XStreamImplicit(itemFieldName = "oper")
    private List<LogOperBean> opers= Lists.newArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSplit() {
        return split;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public List<LogOperBean> getOpers() {
        return opers;
    }

    public void setOpers(List<LogOperBean> opers) {
        this.opers = opers;
    }
}
