package com.moqi.d.d16.d1606;

import net.jcip.annotations.ThreadSafe;

/**
 * ResourceFactory
 * <p/>
 * Lazy initialization holder class idiom
 * 延长初始化占位类模式
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class ResourceFactory {
    public static Resource getResource() {
        return ResourceFactory.ResourceHolder.resource;
    }

    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }

    static class Resource {
    }
}
