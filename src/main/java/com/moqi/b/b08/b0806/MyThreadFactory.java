package com.moqi.b.b08.b0806;

import com.moqi.b.b08.b0807.MyAppThread;

import java.util.concurrent.ThreadFactory;

/**
 * MyThreadFactory
 * <p/>
 * Custom thread factory
 * 自定义的线程工厂
 *
 * @author Brian Goetz and Tim Peierls
 */
public class MyThreadFactory implements ThreadFactory {
    private final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    public Thread newThread(Runnable runnable) {
        return new MyAppThread(runnable, poolName);
    }
}
