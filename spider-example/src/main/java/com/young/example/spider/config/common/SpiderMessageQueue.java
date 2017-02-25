package com.young.example.spider.config.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangyong3 on 2017/2/22.
 */
@XStreamAlias("messageQueue")
public class SpiderMessageQueue implements Serializable{

    @XStreamAsAttribute
    private String classname;

    @XStreamAlias("params")
    private List<SpiderParam> params;

    @XStreamAsAttribute
    private String queueNamePrefix;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public List<SpiderParam> getParams() {
        return params;
    }

    public void setParams(List<SpiderParam> params) {
        this.params = params;
    }

    public String getQueueNamePrefix() {
        return queueNamePrefix;
    }

    public void setQueueNamePrefix(String queueNamePrefix) {
        this.queueNamePrefix = queueNamePrefix;
    }
}
