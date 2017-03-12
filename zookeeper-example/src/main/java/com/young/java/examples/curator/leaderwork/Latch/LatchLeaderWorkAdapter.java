package com.young.java.examples.curator.leaderwork.Latch;

import com.young.java.examples.curator.leaderwork.LeaderWork;
import com.young.java.examples.curator.leaderwork.LeaderWorkException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.leader.LeaderLatch;

import java.util.concurrent.TimeUnit;

/**
 * Created by young.yang on 2017/3/5.
 */
public abstract class LatchLeaderWorkAdapter implements LeaderWork {
    private CuratorFramework framework;
    private String workPath;

    public LatchLeaderWorkAdapter(CuratorFramework framework,String workPath){
        this.framework = framework;
        this.workPath = workPath;
    }

    public abstract void masterWork() throws LeaderWorkException;

    public abstract void slaveWork() throws LeaderWorkException;

    @Override
    public void startWork(boolean loop) throws LeaderWorkException {
         if(CuratorFrameworkState.LATENT == framework.getState()){
             framework.start();
         }else if(CuratorFrameworkState.STOPPED == framework.getState()){
             throw new LeaderWorkException("zk client is closed");
         }
        try {
            if(framework.checkExists().forPath(workPath)==null)
                throw new LeaderWorkException("workPath "+workPath+" is not exist ");
            LeaderLatch latch = new LeaderLatch(framework,workPath);
            latch.start();
            while(true){
                latch.await(10, TimeUnit.SECONDS);
                if (latch.hasLeadership()) {
                    masterWork();
                } else {
                    slaveWork();
                }
                if(!loop){
                    break;
                }
            }
        } catch (Exception e) {
            throw new LeaderWorkException(e);
        }
    }
}
