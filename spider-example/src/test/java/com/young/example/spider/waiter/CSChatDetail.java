package com.young.example.spider.waiter;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/2/21.
 */
public class CSChatDetail implements Serializable{
    private String created_at;

    private String customer;

    private String waiter;

    private UserType type;

    public CSChatDetail(String created_at, String customer, String waiter, UserType type) {
        this.created_at = created_at;
        this.customer = customer;
        this.waiter = waiter;
        this.type = type;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getWaiter() {
        return waiter;
    }

    public void setWaiter(String waiter) {
        this.waiter = waiter;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
}
