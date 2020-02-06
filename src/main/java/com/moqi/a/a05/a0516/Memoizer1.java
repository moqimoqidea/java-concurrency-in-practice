package com.moqi.a.a05.a0516;

import java.math.BigInteger;
import java.util.*;

import net.jcip.annotations.*;

/**
 * Memoizer1
 *
 * Initial cache attempt using HashMap and synchronization
 * 使用 HashMap 和同步机制来从初始化缓存
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Memoizer1 <A, V> implements Computable<A, V> {
    @GuardedBy("this") private final Map<A, V> cache = new HashMap<>();
    private final Computable<A, V> c;

    public Memoizer1(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}


interface Computable <A, V> {
    V compute(A arg) throws InterruptedException;
}

class ExpensiveFunction
        implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) {
        // after deep thought...
        return new BigInteger(arg);
    }
}
