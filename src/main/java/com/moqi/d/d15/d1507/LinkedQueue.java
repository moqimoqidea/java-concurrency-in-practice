package com.moqi.d.d15.d1507;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * LinkedQueue
 * <p/>
 * Insertion in the Michael-Scott nonblocking queue algorithm
 * Michael-Scott 非阻塞算法中的插入算法
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class LinkedQueue<E> {

    private final LinkedQueue.Node<E> dummy = new LinkedQueue.Node<E>(null, null);
    private final AtomicReference<LinkedQueue.Node<E>> head
            = new AtomicReference<LinkedQueue.Node<E>>(dummy);
    private final AtomicReference<LinkedQueue.Node<E>> tail
            = new AtomicReference<LinkedQueue.Node<E>>(dummy);

    public boolean put(E item) {
        LinkedQueue.Node<E> newNode = new LinkedQueue.Node<E>(item, null);
        while (true) {
            LinkedQueue.Node<E> curTail = tail.get();
            LinkedQueue.Node<E> tailNext = curTail.next.get();
            if (curTail == tail.get()) {
                if (tailNext != null) {
                    // Queue in intermediate state, advance tail 队列处于中间状态，推进尾节点
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    // In quiescent state, try inserting new node 处于稳定状态，尝试插入新节点
                    if (curTail.next.compareAndSet(null, newNode)) {
                        // Insertion succeeded, try advancing tail 插入操作成功，尝试推进尾节点
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                }
            }
        }
    }

    private static class Node<E> {
        final E item;
        final AtomicReference<LinkedQueue.Node<E>> next;

        public Node(E item, LinkedQueue.Node<E> next) {
            this.item = item;
            this.next = new AtomicReference<LinkedQueue.Node<E>>(next);
        }
    }
}
