package com.young.java.examples.serializable.json;

import com.young.java.examples.serializable.DSerializable;
import com.young.java.examples.serializable.DSerializationException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by yangyong3 on 2017/7/6.
 */
public class JsonDSerialization<FROM> implements DSerializable<FROM, byte[]> {

    private final ObjectMapper mapper = new ObjectMapper();

    private static final String DEFAULT_ENCODE = "utf-8";

    private Class fromClass;

    public JsonDSerialization(Class fromClass){
        this.fromClass = fromClass;
    }

    @Override
    public byte[] serialization(FROM from) throws DSerializationException {
        String json = null;
        try {
            if (!(from instanceof String)) {
                json = mapper.writeValueAsString(from);
            }
            return json.getBytes(DEFAULT_ENCODE);
        }catch (IOException e){
            e.printStackTrace();
            throw new DSerializationException("serialization error obj is ["+from+"]",e);
        }
    }

    @Override
    public FROM deSerialization(byte[] bytes) throws DSerializationException {
        try {
            String json = new String(bytes, DEFAULT_ENCODE);
            if(fromClass == String.class){
                return (FROM) json;
            }
            return (FROM) mapper.readValue(json,fromClass);
        }catch (IOException e){
            e.printStackTrace();
            throw new DSerializationException("deSerialization error",e);
        }
    }
}
