package com.moqi.d.d16.d1603;

import net.jcip.annotations.NotThreadSafe;

/**
 * UnsafeLazyInitialization
 * <p/>
 * Unsafe lazy initialization
 * 不安全的延迟初始化（不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class UnsafeLazyInitialization {
    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null)
            resource = new Resource(); // unsafe publication
        return resource;
    }

    static class Resource {
    }
}
