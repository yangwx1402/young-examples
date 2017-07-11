package com.young.java.examples.asyn;

/**
 * Created by yangyong3 on 2017/7/11.
 */
public class ExecuableThread implements Runnable {

    private Executable executable;

    private Callback callback;

    public ExecuableThread(Executable executable, Callback callback) {
        this.executable = executable;
        this.callback = callback;
    }

    @Override
    public void run() {
        System.out.println("首先执行业务逻辑");
        ExecuteResult result = null;
        try {
            result = executable.execute();
            if (ExecuteResult.ExecuteStatus.SUCCESS == result.getStatus()) {
                callback.onSuccess(result);
            } else {
                callback.onFailure(result);
            }
        } catch (Exception e) {
            callback.onException(result);
        }
    }
}
