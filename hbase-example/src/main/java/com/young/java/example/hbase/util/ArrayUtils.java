package com.young.java.example.hbase.util;

/**
 * Created by dell on 2016/4/22.
 */
public class ArrayUtils {
    public static String mkString(Object[] objects, String split) {
        if (objects == null || objects.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            if (i == objects.length - 1) {
                sb.append(objects[i].toString());
            } else {
                sb.append(objects[i].toString() + split);
            }
        }
        return sb.toString();
    }
}
