package com.moqi.b.b07.b0710;

import java.util.concurrent.*;

import static com.moqi.a.a05.a0513.LaunderThrowable.launderThrowable;


/**
 * TimedRun
 * <p/>
 * Cancelling a task using Future
 * 通过 Future 来取消任务
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TimedRun {
    private static final ExecutorService taskExec = Executors.newCachedThreadPool();

    public static void timedRun(Runnable r,
                                long timeout, TimeUnit unit)
            throws InterruptedException {
        Future<?> task = taskExec.submit(r);
        try {
            task.get(timeout, unit);
        } catch (TimeoutException e) {
            // task will be cancelled below 接下来任务将被取消
        } catch (ExecutionException e) {
            // exception thrown in task; rethrow 如果在任务中抛出了异常，则重新抛出异常
            throw launderThrowable(e.getCause());
        } finally {
            // Harmless if task already completed 如果任务已经结束，那么执行取消操作也不会带来任何影响
            // interrupt if running 如果任务正在运行，那么将被中断
            task.cancel(true);
        }
    }
}
