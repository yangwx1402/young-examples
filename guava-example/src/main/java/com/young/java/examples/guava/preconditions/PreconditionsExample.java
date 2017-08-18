package com.young.java.examples.guava.preconditions;

import static com.google.common.base.Preconditions.*;

/**
 * Created by yangyong3 on 2017/8/16.
 * Preconditions可以用来判断参数的正确性
 */
public class PreconditionsExample {
    public static void main(String[] args) {
        checkArgument(new Boolean(true));
        checkNotNull(null, "null error");
    }
}
