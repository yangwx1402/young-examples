package com.young.examples.common.hash;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/22.
 */
public class SimHash {
    private Hash hash;

    private int bitSize;

    public SimHash(Hash hash){
        this(64,hash);
    }

    public SimHash(int bitSize,Hash hash){
        this.bitSize = bitSize;
        this.hash = hash;
    }
    /**
     * 计算simHash值
     *
     * @param features
     * @return
     */
    public long simHash(Map<String, Integer> features) {
        int[] simHashBit = new int[bitSize];
        long hashing = 0;
        BitSet temp = null;
        for (Map.Entry<String, Integer> entry : features.entrySet()) {
            hashing = hash.hashing(entry.getKey());
            temp = BitSet.valueOf(new long[] { hashing });
            for (int i = 0; i < bitSize; i++) {
                if (temp.get(i)) {
                    simHashBit[i] += entry.getValue();
                } else {
                    simHashBit[i] -= entry.getValue();
                }
            }
        }
        BitSet simBitValue = BitSet.valueOf(new long[] { 0 });
        for (int i = 0; i < bitSize; i++) {
            if (simHashBit[i] > 0) {
                simBitValue.set(i, true);
            } else {
                simBitValue.set(i, false);
            }
        }
        return simBitValue.toLongArray()[0];
    }

    /**
     * 计算海明距离
     *
     * @param simhash1
     * @param simhash2
     * @return
     */
    public int hanMingDistance(long simhash1, long simhash2) {
        BitSet bit1 = BitSet.valueOf(new long[] { simhash1 });
        BitSet bit2 = BitSet.valueOf(new long[] { simhash2 });
        bit1.xor(bit2);
        return bit1.cardinality();
    }

    public static void main(String[] args) {
        SimHash hash = new SimHash(64,new MD5Hash());
        Map<String, Integer> features = new HashMap<String, Integer>();
        features.put("呵呵", 20);
        features.put("他们", 14);
        features.put("都说", 13);
        features.put("其实", 21);
        long sim1 = hash.simHash(features);

        Map<String, Integer> features1 = new HashMap<String, Integer>();
        features1.put("呵呵", 20);
        features1.put("他们", 14);
        features1.put("都说", 13);
        features1.put("其实", 20);
        long sim2 = hash.simHash(features1);

        System.out.println(hash.hanMingDistance(sim1, sim2));
    }
}
