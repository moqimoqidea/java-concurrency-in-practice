package com.moqi.a.a04.a0403;

import apple.laf.JRSUIConstants;
import net.jcip.annotations.*;

/**
 * PrivateLock
 * <p/>
 * Guarding state with a private lock
 * 通过一个私有锁来保护状态
 *
 * @author Brian Goetz and Tim Peierls
 */
public class PrivateLock {
    private final Object myLock = new Object();
    @GuardedBy("myLock")
    JRSUIConstants.Widget widget;

    void someMethod() {
        synchronized (myLock) {
            // Access or modify the state of widget
        }
    }
}
