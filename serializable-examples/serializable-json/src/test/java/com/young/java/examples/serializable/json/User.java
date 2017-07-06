package com.young.java.examples.serializable.json;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/7/6.
 */
public class User implements Serializable{

    private String name;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
