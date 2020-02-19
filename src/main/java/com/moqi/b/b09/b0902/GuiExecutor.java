package com.moqi.b.b09.b0902;

import com.moqi.b.b09.b0901.SwingUtilities;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * GuiExecutor
 * <p/>
 * Executor built atop SwingUtilities
 * 基于 SwingUtilities 构建的 Executor
 *
 * @author Brian Goetz and Tim Peierls
 */
public class GuiExecutor extends AbstractExecutorService {
    // Singletons have a private constructor and a public factory
    // 单例模式
    private static final GuiExecutor instance = new GuiExecutor();

    private GuiExecutor() {
    }

    public static GuiExecutor instance() {
        return instance;
    }

    public void execute(Runnable r) {
        if (SwingUtilities.isEventDispatchThread())
            r.run();
        else
            SwingUtilities.invokeLater(r);
    }

    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit)
            throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }
}
