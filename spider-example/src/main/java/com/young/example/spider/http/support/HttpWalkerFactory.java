package com.young.example.spider.http.support;

import com.young.example.spider.http.HttpWalker;

/**
 * Created by yangyong3 on 2017/2/23.
 */
public class HttpWalkerFactory {

    public static HttpWalker getHttpWalker(){
        return new HttpClientWalker();
    }
}
