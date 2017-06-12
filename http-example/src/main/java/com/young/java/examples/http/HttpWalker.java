package com.young.java.examples.http;

import java.io.IOException;

/**
 * Created by yangyong3 on 2017/2/20.
 */
public interface HttpWalker {

    public Response get(Request request) throws IOException;

    public Response post(Request request) throws IOException;

    public Response delete(Request request) throws IOException;
}
