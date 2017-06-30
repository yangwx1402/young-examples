package com.young.java.examples.pattern.constructor.prototype;

/**
 * Created by yangyong3 on 2017/6/30.
 */
public class User implements Cloneable {
    private String name;

    private int age;

    private User child;

    public User getChild() {
        return child;
    }

    public void setChild(User child) {
        this.child = child;
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

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
