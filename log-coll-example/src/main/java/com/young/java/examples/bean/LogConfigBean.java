package com.young.java.examples.bean;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by young.yang on 2016/11/12.
 */
@XStreamAlias("root")
public class LogConfigBean {
    @XStreamAsAttribute
    private String name;
    @XStreamImplicit(itemFieldName = "module")
    private List<LogModuleBean> moduler= Lists.newArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LogModuleBean> getModuler() {
        return moduler;
    }

    public void setModuler(List<LogModuleBean> moduler) {
        this.moduler = moduler;
    }

    public LogStorageBean getStorage() {
        return storage;
    }

    public void setStorage(LogStorageBean storage) {
        this.storage = storage;
    }

    @XStreamAlias("storage")
    private LogStorageBean storage;


}
