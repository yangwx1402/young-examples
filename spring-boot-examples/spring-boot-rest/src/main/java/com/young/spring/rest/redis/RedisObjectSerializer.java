package com.young.spring.rest.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by yangyong3 on 2017/2/4.
 */
public class RedisObjectSerializer implements RedisSerializer<Object> {

    private Converter<Object, byte[]> serializer = new SerializingConverter();
    private Converter<byte[], Object> deserializer = new DeserializingConverter();
    private static final byte[] EMPTY_ARRAY = new byte[0];

    private boolean isEmpty(byte[] bytes){
        if(bytes==null||bytes.length<=0)
            return true;
        return false;
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if(o == null)
            return EMPTY_ARRAY;
        return serializer.convert(o);
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if(isEmpty(bytes))
            return null;
        return deserializer.convert(bytes);
    }
}
