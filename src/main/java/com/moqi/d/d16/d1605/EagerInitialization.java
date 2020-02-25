package com.moqi.d.d16.d1605;

import net.jcip.annotations.ThreadSafe;

/**
 * EagerInitialization
 * <p/>
 * Eager initialization
 * 提前初始化
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class EagerInitialization {
    private static Resource resource = new Resource();

    public static Resource getResource() {
        return resource;
    }

    static class Resource {
    }
}
