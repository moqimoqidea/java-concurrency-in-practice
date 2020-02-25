package com.moqi.d.d16.d1604;

import net.jcip.annotations.ThreadSafe;

/**
 * SafeLazyInitialization
 * <p/>
 * Thread-safe lazy initialization
 * 线程安全的延迟初始化
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class SafeLazyInitialization {
    private static Resource resource;

    public synchronized static Resource getInstance() {
        if (resource == null)
            resource = new Resource();
        return resource;
    }

    static class Resource {
    }
}
