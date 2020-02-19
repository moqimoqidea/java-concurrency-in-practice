package com.moqi.b.b08.b0818;

import com.moqi.b.b08.b0813.Puzzle;
import com.moqi.b.b08.b0814.PuzzleNode;
import com.moqi.b.b08.b0816.ConcurrentPuzzleSolver;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * PuzzleSolver
 * <p/>
 * Solver that recognizes when no solution exists
 * 在解决器中找不到解答
 *
 * @author Brian Goetz and Tim Peierls
 */
public class PuzzleSolver<P, M> extends ConcurrentPuzzleSolver<P, M> {
    private final AtomicInteger taskCount = new AtomicInteger(0);

    PuzzleSolver(Puzzle<P, M> puzzle) {
        super(puzzle);
    }

    protected Runnable newTask(P p, M m, PuzzleNode<P, M> n) {
        return new CountingSolverTask(p, m, n);
    }

    class CountingSolverTask extends SolverTask {
        CountingSolverTask(P pos, M move, PuzzleNode<P, M> prev) {
            super(pos, move, prev);
            taskCount.incrementAndGet();
        }

        public void run() {
            try {
                super.run();
            } finally {
                if (taskCount.decrementAndGet() == 0)
                    solution.setValue(null);
            }
        }
    }
}
