package com.young.java.examples.serializable.java;

import com.young.java.examples.serializable.DSerializable;
import com.young.java.examples.serializable.DSerializationException;

import java.io.*;

/**
 * Created by yangyong3 on 2017/7/6.
 */
public class JavaDSerialization<FROM extends Serializable> implements DSerializable<FROM, byte[]> {
    @Override
    public byte[] serialization(FROM from) throws DSerializationException {
        ObjectOutputStream output = null;
        ByteArrayOutputStream result = null;
        try {
            result = new ByteArrayOutputStream();
            output = new ObjectOutputStream(result);
            output.writeObject(from);
            output.flush();
            return result.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DSerializationException("serialization object error obj is -[" + from + "]", e);
        } finally {
            try {
                if (output != null)
                    output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public FROM deSerialization(byte[] bytes) throws DSerializationException {
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream input = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            input = new ObjectInputStream(byteArrayInputStream);
            return (FROM) input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DSerializationException("deSerialization error ",e);
        } finally {
            try {
                if (input != null)
                    input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
