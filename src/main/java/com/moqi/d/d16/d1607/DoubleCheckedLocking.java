package com.moqi.d.d16.d1607;

import net.jcip.annotations.NotThreadSafe;

/**
 * DoubleCheckedLocking
 * <p/>
 * Double-checked-locking antipattern
 * 双重检查加锁（不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class DoubleCheckedLocking {
    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null) {
            synchronized (DoubleCheckedLocking.class) {
                if (resource == null)
                    resource = new Resource();
            }
        }
        return resource;
    }

    static class Resource {

    }
}
