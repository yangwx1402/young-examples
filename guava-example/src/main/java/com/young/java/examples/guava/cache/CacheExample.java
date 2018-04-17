package com.young.java.examples.guava.cache;

import java.util.concurrent.ExecutionException;

/**
 * @author shazam
 * @DATE 2018/4/17
 */
public abstract class CacheExample {

    public abstract User getUser(String name) throws ExecutionException ;
}
