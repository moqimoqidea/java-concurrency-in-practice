package com.moqi.a03.a0314;

import javax.xml.ws.Holder;

/**
 * StuffIntoPublic
 * <p/>
 * Unsafe publication
 * 在没有同步的情况下发布对象（不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */
public class StuffIntoPublic {
    public Holder holder;

    public void initialize() {
        holder = new Holder(42);
    }
}
