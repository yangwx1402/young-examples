package com.young.examples.common.hash;

import com.google.common.hash.Hashing;

/**
 * Created by Administrator on 2016/5/22.
 */
public class Sha1Hash implements Hash {
    @Override
    public Long hashing(String string) {
        return Hashing.sha1().hashBytes(string.getBytes()).asLong();
    }
}
