package com.young.java.examples.curator.leaderwork.Latch;

import com.young.java.examples.curator.client.ZKClient;
import com.young.java.examples.curator.leaderwork.LeaderWorkException;
import org.apache.curator.framework.CuratorFramework;


/**
 * Created by young.yang on 2017/3/5.
 * latch leader work可以實現一個分佈式的生產者和消費者，leader來生成任務，follow來完成任務
 */
public class LatchLeaderWorkExample extends LatchLeaderWorkAdapter{
    //當然這個path必須是先存在的一個path
    private static final String latchLeaderWorkPath = "/task/lock";

    public LatchLeaderWorkExample(CuratorFramework framework, String workPath) {
        super(framework, workPath);
    }

    public LatchLeaderWorkExample(){
        this(new ZKClient().getZKClient(),latchLeaderWorkPath);
    }

    @Override
    public void masterWork() throws LeaderWorkException {
        System.out.println("我是领导，我来分配任务");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void slaveWork() throws LeaderWorkException {
        System.out.println("我是小兵，我来执行任务");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        LatchLeaderWorkExample work = new LatchLeaderWorkExample();
        work.startWork(true);
    }
}
