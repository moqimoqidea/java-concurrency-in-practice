package com.moqi.d.d14.d1405;

import com.moqi.d.d14.d1402.BaseBoundedBuffer;
import net.jcip.annotations.ThreadSafe;

/**
 * SleepyBoundedBuffer
 * <p/>
 * Bounded buffer using crude blocking
 * 使用简单阻塞实现的有界缓存
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SleepyBoundedBuffer<V> extends BaseBoundedBuffer<V> {
    int SLEEP_GRANULARITY = 60;

    public SleepyBoundedBuffer() {
        this(100);
    }

    public SleepyBoundedBuffer(int size) {
        super(size);
    }

    public void put(V v) throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(v);
                    return;
                }
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }

    public V take() throws InterruptedException {
        while (true) {
            synchronized (this) {
                if (!isEmpty())
                    return doTake();
            }
            Thread.sleep(SLEEP_GRANULARITY);
        }
    }
}
