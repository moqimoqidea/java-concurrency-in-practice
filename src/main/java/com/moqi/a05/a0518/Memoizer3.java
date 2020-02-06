package com.moqi.a05.a0518;

import com.moqi.a05.a0513.LaunderThrowable;
import org.apache.commons.lang3.concurrent.Computable;

import java.util.Map;
import java.util.concurrent.*;

/**
 * Memoizer3
 * <p/>
 * Memoizing wrapper using FutureTask
 * 基于 FutureTask 的 Memoizing 封装器
 *
 * @author Brian Goetz and Tim Peierls
 */
public class Memoizer3<A, V> implements Computable<A, V> {
    private final Map<A, Future<V>> cache
            = new ConcurrentHashMap<A, Future<V>>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(final A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Callable<V> eval = new Callable<V>() {
                @Override
                public V call() throws InterruptedException {
                    return c.compute(arg);
                }
            };
            FutureTask<V> ft = new FutureTask<V>(eval);
            f = ft;
            cache.put(arg, ft);
            ft.run(); // call to c.compute happens here
        }
        try {
            return f.get();
        } catch (ExecutionException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }
}
