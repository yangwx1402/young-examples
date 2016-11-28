package com.young.java.examples.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by young.yang on 2016/11/12.
 */
@XStreamAlias("arg")
public class LogArgBean implements Comparable<LogArgBean>{

    @XStreamAsAttribute
    private Integer index;
    @XStreamAsAttribute
    private String name;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(LogArgBean o) {
        return this.getIndex().compareTo(o.getIndex());
    }
}
