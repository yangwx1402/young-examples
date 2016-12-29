package com.young.java.examples.mq;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2016/12/13.
 */
public class SpiderMessagePool implements Serializable {

    private Integer poolSize;
    private String classname;

    public Integer getPoolSize() {
        return poolSize;
    }

    public void setPoolSize(Integer poolSize) {
        this.poolSize = poolSize;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
    
}
