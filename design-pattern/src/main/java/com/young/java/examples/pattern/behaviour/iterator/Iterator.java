package com.young.java.examples.pattern.behaviour.iterator;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public interface Iterator<T> {
    public T previous();

    public T next();

    public T first();

    public boolean hasNext();
}
