package com.young.java.examples.curator.leaderwork;

import com.young.java.examples.curator.exception.DistributedException;

/**
 * Created by young.yang on 2017/3/5.
 */
public class LeaderWorkException extends DistributedException {

    public LeaderWorkException(String message) {
        super(message);
    }

    public LeaderWorkException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeaderWorkException(Throwable cause) {
        super(cause);
    }
}
