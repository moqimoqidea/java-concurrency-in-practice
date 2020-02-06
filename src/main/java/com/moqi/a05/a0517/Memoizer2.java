package com.moqi.a05.a0517;

import org.apache.commons.lang3.concurrent.Computable;

import java.util.*;
import java.util.concurrent.*;

/**
 * Memoizer2
 * <p/>
 * Replacing HashMap with ConcurrentHashMap
 * 用 ConcurrentHashMap 来代替 HashMap
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Memoizer2 <A, V> implements Computable<A, V> {
    private final Map<A, V> cache = new ConcurrentHashMap<A, V>();
    private final Computable<A, V> c;

    public Memoizer2(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result == null) {
            result = c.compute(arg);
            cache.put(arg, result);
        }
        return result;
    }
}
