package com.moqi.a.a04.a0405;

import net.jcip.annotations.*;

/**
 * MutablePoint
 * <p/>
 * Mutable Point class similar to java.awt.Point
 * 与 java.awt.Point 类似的可变 Point 类（不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */
@NotThreadSafe
public class MutablePoint {
    public int x, y;

    public MutablePoint() {
        x = 0;
        y = 0;
    }

    public MutablePoint(MutablePoint p) {
        this.x = p.x;
        this.y = p.y;
    }
}
