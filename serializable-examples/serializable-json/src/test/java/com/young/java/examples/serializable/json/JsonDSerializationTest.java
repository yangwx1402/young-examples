package com.young.java.examples.serializable.json;

import com.young.java.examples.serializable.DSerializable;
import com.young.java.examples.serializable.DSerializationException;

/**
 * Created by yangyong3 on 2017/7/6.
 */
public class JsonDSerializationTest {
    public static void main(String[] args) throws DSerializationException {
        DSerializable<User,byte[]> dSerializable = new JsonDSerialization<User>(User.class);
        User user = new User();
        user.setName("哈哈");
        user.setAge(100);
        byte[] bytes = dSerializable.serialization(user);
        System.out.println(bytes);
        User user2 = dSerializable.deSerialization(bytes);
        System.out.println(user2.getName());
    }
}
