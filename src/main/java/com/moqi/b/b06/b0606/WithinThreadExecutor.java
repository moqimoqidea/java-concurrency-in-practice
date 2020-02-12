package com.moqi.b.b06.b0606;

import java.util.concurrent.Executor;

/**
 * WithinThreadExecutor
 * <p/>
 * Executor that executes tasks synchronously in the calling thread
 * 在调用线程中以同步方式执行所有任务的 Executor
 *
 * @author Brian Goetz and Tim Peierls
 */
public class WithinThreadExecutor implements Executor {
    public void execute(Runnable r) {
        r.run();
    }

}
