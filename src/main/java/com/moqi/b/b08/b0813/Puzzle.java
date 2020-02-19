package com.moqi.b.b08.b0813;

import java.util.Set;

/**
 * Puzzle
 * <p/>
 * Abstraction for puzzles like the 'sliding blocks puzzle'
 * 表示"搬箱子"之类谜题的抽象类
 *
 * @author Brian Goetz and Tim Peierls
 */
public interface Puzzle <P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);
}
