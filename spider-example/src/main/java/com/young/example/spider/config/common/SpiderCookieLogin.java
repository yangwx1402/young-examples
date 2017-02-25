package com.young.example.spider.config.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.io.Serializable;

/**
 * Created by yangyong3 on 2017/2/22.
 */
@XStreamAlias("login")
public class SpiderCookieLogin implements Serializable{

    @XStreamAsAttribute
    private String type;
    @XStreamAsAttribute
    private String script;

    @XStreamAsAttribute
    private String queue;

    @XStreamAsAttribute
    private String username;
    @XStreamAsAttribute
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }
}
