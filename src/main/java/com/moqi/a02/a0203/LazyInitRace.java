package com.moqi.a02.a0203;

import net.jcip.annotations.NotThreadSafe;

/**
 * LazyInitRace
 * <p>
 * Race condition in lazy initialization
 * 延迟初始化中的竞态条件（不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */

@NotThreadSafe
public class LazyInitRace {
    private ExpensiveObject instance = null;

    public ExpensiveObject getInstance() {
        if (instance == null) {
            instance = new ExpensiveObject();
        }

        return instance;
    }
}

class ExpensiveObject {
}
