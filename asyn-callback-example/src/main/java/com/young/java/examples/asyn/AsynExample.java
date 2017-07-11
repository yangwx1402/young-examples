package com.young.java.examples.asyn;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangyong3 on 2017/7/11.
 */
public class AsynExample {

    private static Random random = new Random();

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    private static class TestExecuteResult extends ExecuteResult {

        public TestExecuteResult(ExecuteStatus status) {
            super(status);
        }
    }

    private static Executable executable = new Executable() {
        @Override
        public ExecuteResult execute() throws Exception {
            int index = random.nextInt(10);
            if (index < 4) {
                return new TestExecuteResult(ExecuteResult.ExecuteStatus.SUCCESS);
            } else if (index > 7) {
                throw new Exception("error");
            } else
                return new TestExecuteResult(ExecuteResult.ExecuteStatus.FAILURE);
        }
    };

    private static Callback callback = new Callback() {
        @Override
        public void onSuccess(ExecuteResult result) {
            System.out.println("success");
        }

        @Override
        public void onFailure(ExecuteResult result) {
            System.out.println("failure");
        }

        @Override
        public void onException(ExecuteResult result) {
            System.out.println("exception");
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            threadPool.execute(new ExecuableThread(executable, callback));
        }
        threadPool.shutdown();
    }
}
