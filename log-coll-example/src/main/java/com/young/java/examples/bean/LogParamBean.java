package com.young.java.examples.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by young.yang on 2016/11/12.
 */
@XStreamAlias("param")
public class LogParamBean{
    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
