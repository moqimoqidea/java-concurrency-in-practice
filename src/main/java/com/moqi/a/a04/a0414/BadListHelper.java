package com.moqi.a.a04.a0414;

import java.util.*;

import net.jcip.annotations.*;

/**
 * BadListHelper
 * <p/>
 * Examples of thread-safe and non-thread-safe implementations of
 * put-if-absent helper methods for List
 * 非线程安全的 若没有则添加 （不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class BadListHelper <E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !list.contains(x);
        if (absent) {
            list.add(x);
        }
        return absent;
    }
}

