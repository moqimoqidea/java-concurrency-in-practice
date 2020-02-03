package com.moqi.a03.a0311;

import net.jcip.annotations.Immutable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * ThreeStooges
 * <p/>
 * Immutable class built out of mutable underlying objects,
 * demonstration of candidate for lock elision
 * 在可变对象基础上构建的不可变类
 *
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<String>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }

    public String getStoogeNames() {
        List<String> stooges = new Vector<>();
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
        return stooges.toString();
    }
}
