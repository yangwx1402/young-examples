package com.young.java.examples.java18.lambda;

import java.io.Serializable;

/**
 * @author shazam
 * @DATE 2018/3/23
 */
public class User implements Serializable {

    private static final long serialVersionUID = -4632837254612649981L;

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
