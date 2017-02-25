package com.young.example.spider.waiter;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/2/20.
 */
public class CSWaiter implements Serializable{
    private String name;

    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CSWaiter(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
}
