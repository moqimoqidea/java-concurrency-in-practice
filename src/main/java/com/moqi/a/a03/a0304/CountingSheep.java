package com.moqi.a.a03.a0304;

/**
 * CountingSheep
 * <p/>
 * Counting sheep
 * 数绵羊
 *
 * @author Brian Goetz and Tim Peierls
 */
public class CountingSheep {
    volatile boolean asleep;

    void tryToSleep() {
        while (!asleep) {
            countSomeSheep();
        }
    }

    void countSomeSheep() {
        // One, two, three...
    }
}

