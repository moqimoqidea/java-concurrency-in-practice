package com.moqi.a.a02.a0201;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.math.BigInteger;

/**
 * StatelessFactorizer
 * <p>
 * A stateless servlet
 * 一个无状态的 Servlet
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class StatelessFactorizer extends GenericServlet implements Servlet {

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}
