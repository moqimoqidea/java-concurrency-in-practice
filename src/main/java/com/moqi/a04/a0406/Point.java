package com.moqi.a04.a0406;

import net.jcip.annotations.*;

/**
 * Point
 * <p/>
 * Immutable Point class used by DelegatingVehicleTracker
 * 在 DelegatingVehicleTracker 中使用不可变的 Point 类
 *
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
public class Point {
    public final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
