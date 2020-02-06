package com.moqi.a.a04.a0415;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * GoodListHelper
 * <p/>
 * 通过客户端加锁来实现 若没有则添加
 *
 * @author Brian Goetz and Tim Peierls
 */

@ThreadSafe
public class GoodListHelper<E> {
    public List<E> list = Collections.synchronizedList(new ArrayList<>());

    public boolean putIfAbsent(E x) {
        synchronized (list) {
            boolean absent = !list.contains(x);
            if (absent) {
                list.add(x);
            }
            return absent;
        }
    }
}

