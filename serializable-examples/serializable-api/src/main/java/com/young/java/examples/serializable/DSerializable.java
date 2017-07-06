package com.young.java.examples.serializable;

/**
 * Created by yangyong3 on 2017/7/6.
 */
public interface DSerializable<FROM, TO> {

    TO serialization(FROM from) throws DSerializationException;

    FROM deSerialization(TO to) throws DSerializationException;
}
