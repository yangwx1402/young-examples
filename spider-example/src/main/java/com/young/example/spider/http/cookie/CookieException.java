package com.young.example.spider.http.cookie;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class CookieException extends Exception {

    public CookieException(String message) {
        super(message);
    }

    public CookieException(String message, Throwable cause) {
        super(message, cause);
    }
}
