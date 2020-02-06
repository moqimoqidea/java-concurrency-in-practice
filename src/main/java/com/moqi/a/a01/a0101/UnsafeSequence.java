package com.moqi.a.a01.a0101;

import net.jcip.annotations.NotThreadSafe;

/**
 * UnsafeSequence
 * 非线程安全的数值序列生成器
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class UnsafeSequence {
    private int value;

    /**
     * Returns a unique value.
     */
    public int getNext() {
        return value++;
    }
}
