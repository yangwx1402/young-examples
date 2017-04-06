package com.young.java.examples.configcenter;

import java.io.Serializable;

/**
 * Created by dell on 2016/6/30.
 */
public class ConfigBean implements Serializable {
    private String ip;

    private Integer port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String toString(){
        return ip+":"+port;
    }
}
