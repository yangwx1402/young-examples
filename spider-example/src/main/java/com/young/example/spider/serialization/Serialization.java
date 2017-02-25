package com.young.example.spider.serialization;

/**
 * Created by young.yang on 2017/2/25.
 */
public interface Serialization<from,to> {
    public to serialization(from f) throws SerializationException;

    public from unserialization(to t,Class<from> fromClass)  throws SerializationException;
}
