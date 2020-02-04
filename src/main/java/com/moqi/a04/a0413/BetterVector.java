package com.moqi.a04.a0413;

import java.util.*;

import net.jcip.annotations.*;

/**
 * BetterVector
 * <p/>
 * Extending Vector to have a put-if-absent method
 * 扩展 Vector 并增加一个 若没有则添加 的方法
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class BetterVector <E> extends Vector<E> {
    /**
     * When extending a serializable class, you should redefine serialVersionUID
     * 扩展可序列化的类时，应重新定义serialVersionUID
     */
    static final long serialVersionUID = -3963416950630760754L;

    public synchronized boolean putIfAbsent(E x) {
        boolean absent = !contains(x);
        if (absent) {
            add(x);
        }
        return absent;
    }
}
