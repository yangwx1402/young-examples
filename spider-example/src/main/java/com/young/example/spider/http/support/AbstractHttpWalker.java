package com.young.example.spider.http.support;

import com.young.example.spider.http.Request;

import java.io.IOException;

/**
 * Created by yangyong3 on 2017/2/20.
 */
public abstract class AbstractHttpWalker {


    protected void checkRequest(Request request) throws IOException {
        if (request == null)
            throw new IOException("request is null");
        if (request.getUrl() == null || "".equals(request.getUrl().trim()))
            throw new IOException("request's url is null");
    }
}
