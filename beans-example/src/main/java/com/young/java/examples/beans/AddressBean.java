package com.young.java.examples.beans;

import java.io.Serializable;

/**
 * Created by dell on 2016/8/12.
 */
public class AddressBean implements Serializable{
    private String city;

    private String address;

    private String code;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
