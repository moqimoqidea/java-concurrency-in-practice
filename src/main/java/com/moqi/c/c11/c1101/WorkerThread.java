package com.moqi.c.c11.c1101;

import java.util.concurrent.BlockingQueue;

/**
 * WorkerThread
 * <p/>
 * Serialized access to a task queue
 * 对任务队列的串行访问
 *
 * @author Brian Goetz and Tim Peierls
 */

public class WorkerThread extends Thread {
    private final BlockingQueue<Runnable> queue;

    public WorkerThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                Runnable task = queue.take();
                task.run();
            } catch (InterruptedException e) {
                break; /* Allow thread to exit 允许线程退出 */
            }
        }
    }
}
