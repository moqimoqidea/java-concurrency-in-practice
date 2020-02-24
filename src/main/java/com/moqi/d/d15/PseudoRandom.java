package com.moqi.d.d15;

/**
 * PseudoRandom
 * 伪随机数字生成器：下一个随机数字需要用到上一个随机数字
 *
 * @author Brian Goetz and Tim Peierls
 */
public class PseudoRandom {

    public int calculateNext(int prev) {
        prev ^= prev << 6;
        prev ^= prev >>> 21;
        prev ^= (prev << 7);
        return prev;
    }

}
