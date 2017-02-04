package com.young.spring.rest.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/2/3.
 */
@Entity
@Table(name="spring_boot_user")
public class User implements Serializable{
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 10)
    private String username;
    @Column
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User(String username, Integer age) {
        this.username = username;
        this.age = age;
    }

    public User(){}
}
