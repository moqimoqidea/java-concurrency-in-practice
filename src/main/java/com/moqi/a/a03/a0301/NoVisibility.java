package com.moqi.a.a03.a0301;

/**
 * NoVisibility
 * <p/>
 * Sharing variables without synchronization
 * 在没有同步的情况下共享变量（不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */

public class NoVisibility {
    private static boolean ready;
    private static int number;

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }
    }
}
