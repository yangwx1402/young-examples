package com.young.java.examples.curator.leaderwork;

/**
 * Created by young.yang on 2017/3/5.
 */
public interface LeaderWork {

    public void startWork(boolean loop) throws LeaderWorkException;
}
