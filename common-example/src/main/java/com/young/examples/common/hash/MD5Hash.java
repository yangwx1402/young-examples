package com.young.examples.common.hash;

import com.google.common.hash.Hashing;

/**
 * Created by Administrator on 2016/5/22.
 */
public class MD5Hash implements Hash {
    @Override
    public Long hashing(String string) {
        return Hashing.md5().hashBytes(string.getBytes()).asLong();
    }
}
