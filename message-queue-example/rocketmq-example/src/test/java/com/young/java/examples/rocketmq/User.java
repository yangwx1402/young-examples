package com.young.java.examples.rocketmq;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public class User implements Serializable{
    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
