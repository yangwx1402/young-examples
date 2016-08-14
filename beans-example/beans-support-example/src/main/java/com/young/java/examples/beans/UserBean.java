package com.young.java.examples.beans;

import java.io.Serializable;

/**
 * Created by dell on 2016/8/12.
 */
public class UserBean implements Serializable{

    private String name;

    private int age;

    private AddressBean address;

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

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

}
