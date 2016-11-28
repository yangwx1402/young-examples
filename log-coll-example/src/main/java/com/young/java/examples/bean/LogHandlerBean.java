package com.young.java.examples.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * Created by young.yang on 2016/11/12.
 */
@XStreamAlias("handler")
public class LogHandlerBean {
    @XStreamAsAttribute
    private String name;
    @XStreamAsAttribute
    private String classname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
