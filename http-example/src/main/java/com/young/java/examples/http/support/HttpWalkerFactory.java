package com.young.java.examples.http.support;


import com.young.java.examples.http.HttpWalker;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class HttpWalkerFactory {

    public static HttpWalker getHttpWalker(){
        return new HttpClientWalker();
    }
}
