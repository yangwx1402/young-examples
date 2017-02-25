package com.young.example.spider.http.cookie;

/**
 * Created by yangyong3 on 2017/2/22.
 */
public class SpiderCookies {

    private String cookieString;

    private CookieState cookieState;

    public SpiderCookies(String cookieString, CookieState cookieState) {
        this.cookieString = cookieString;
        this.cookieState = cookieState;
    }

    public String getCookieString() {
        return cookieString;
    }

    public void setCookieString(String cookieString) {
        this.cookieString = cookieString;
    }

    public CookieState getCookieState() {
        return cookieState;
    }

    public void setCookieState(CookieState cookieState) {
        this.cookieState = cookieState;
    }
}
