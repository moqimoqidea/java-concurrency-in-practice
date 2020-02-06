package com.moqi.a.a05.a0513;

/**
 * StaticUtilities
 * source: https://github.com/jcip/jcip.github.com/blob/master/listings/LaunderThrowable.java
 *
 * @author Brian Goetz and Tim Peierls
 */
public class LaunderThrowable {

    /**
     * Coerce an unchecked Throwable to a RuntimeException
     * 强制将未检查的 Throwable 转换为 RuntimeException
     * <p/>
     * If the Throwable is an Error, throw it; if it is a
     * RuntimeException return it, otherwise throw IllegalStateException
     *
     * 如果是 Error，抛出去；
     * 如果是 RuntimeException，返回；
     * 否则抛出 IllegalStateException。
     */
    public static RuntimeException launderThrowable(Throwable t) {
        if (t instanceof RuntimeException) {
            return (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new IllegalStateException("Not unchecked", t);
        }
    }
}
