package com.moqi.a05.a0510;

import java.util.concurrent.*;

/**
 * TaskRunnable
 * <p/>
 * Restoring the interrupted status so as not to swallow the interrupt
 * 恢复中断状态以避免屏蔽中断
 *
 * @author Brian Goetz and Tim Peierls
 */
public class TaskRunnable implements Runnable {
    BlockingQueue<Task> queue;

    @Override
    public void run() {
        try {
            processTask(queue.take());
        } catch (InterruptedException e) {
            // restore interrupted status
            Thread.currentThread().interrupt();
        }
    }

    void processTask(Task task) {
        // Handle the task
    }

    interface Task {
    }
}
