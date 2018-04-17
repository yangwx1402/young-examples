package com.young.java.examples.guava.cache;

import java.io.Serializable;

/**
 * @author shazam
 * @DATE 2018/4/17
 */
public class User implements Serializable {
    private static final long serialVersionUID = -3854170221917554661L;
    private String name;

    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
