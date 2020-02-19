package com.moqi.b.b08.b0814;

import net.jcip.annotations.Immutable;

import java.util.LinkedList;
import java.util.List;

/**
 * PuzzleNode
 * <p/>
 * Link node for the puzzle solving framework
 * 用于谜题解决框架的链表节点
 *
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
public class PuzzleNode <P, M> {
    public final P pos;
    final M move;
    final PuzzleNode<P, M> prev;

    public PuzzleNode(P pos, M move, PuzzleNode<P, M> prev) {
        this.pos = pos;
        this.move = move;
        this.prev = prev;
    }

    public List<M> asMoveList() {
        List<M> solution = new LinkedList<M>();
        for (PuzzleNode<P, M> n = this; n.move != null; n = n.prev)
            solution.add(0, n.move);
        return solution;
    }
}
