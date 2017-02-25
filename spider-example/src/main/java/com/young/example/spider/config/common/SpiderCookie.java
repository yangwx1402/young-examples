package com.young.example.spider.config.common;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

/**
 * Created by yangyong3 on 2017/2/22.
 */
@XStreamAlias("cookies")
public class SpiderCookie {

    @XStreamAsAttribute
    private String temppath;
    @XStreamImplicit(itemFieldName = "login")
    private List<SpiderCookieLogin> logins;
    @XStreamAsAttribute
    private String storage;

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getTemppath() {
        return temppath;
    }

    public void setTemppath(String temppath) {
        this.temppath = temppath;
    }

    public List<SpiderCookieLogin> getLogins() {
        return logins;
    }

    public void setLogins(List<SpiderCookieLogin> logins) {
        this.logins = logins;
    }
}
