package com.young.java.examples.pattern.behaviour.iterator;

/**
 * Created by yangyong3 on 2017/7/3.
 */
public interface Collection<T> {
    public void add(T t);

    public T get(int index);


    public Iterator<T> iterator();

    public int size();
}
