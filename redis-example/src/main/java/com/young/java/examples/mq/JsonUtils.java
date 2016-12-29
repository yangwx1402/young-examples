package com.young.java.examples.mq;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by yangyong3 on 2016/11/29.
 */
public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }

    public static <T> T fromJson(String json,Class<T> tClass) throws IOException {
        return mapper.readValue(json,tClass);
    }
}
