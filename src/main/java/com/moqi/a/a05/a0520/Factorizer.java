package com.moqi.a.a05.a0520;

import java.math.BigInteger;
import javax.servlet.*;

import com.moqi.a.a05.a0519.Memoizer;
import net.jcip.annotations.*;
import org.apache.commons.lang3.concurrent.Computable;

/**
 * Factorizer
 * <p/>
 * Factorizing servlet that caches results using Memoizer
 * 在因式分解 Servlet 中使用 Memoizer 来缓存结果
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class Factorizer extends GenericServlet implements Servlet {
    private final Computable<BigInteger, BigInteger[]> c =
            new Computable<BigInteger, BigInteger[]>() {
                @Override
                public BigInteger[] compute(BigInteger arg) {
                    return factor(arg);
                }
            };
    private final Computable<BigInteger, BigInteger[]> cache
            = new Memoizer<BigInteger, BigInteger[]>(c);

    @Override
    public void service(ServletRequest req,
                        ServletResponse resp) {
        try {
            BigInteger i = extractFromRequest(req);
            encodeIntoResponse(resp, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(resp, "factorization interrupted");
        }
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    void encodeError(ServletResponse resp, String errorString) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}
