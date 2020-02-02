package com.moqi.a03.a0304;

/**
 * CountingSheep
 * <p/>
 * Counting sheep
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

