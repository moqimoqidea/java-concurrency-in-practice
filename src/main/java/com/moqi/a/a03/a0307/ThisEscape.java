package com.moqi.a.a03.a0307;

/**
 * ThisEscape
 * <p/>
 * Implicitly allowing the this reference to escape
 * 隐式地使 this 引用逸出（不要这么做）
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ThisEscape {
    public ThisEscape(EventSource source) {
        // 这里可以替换为 Lambda 表达式：source.registerListener(this::doSomething);
        source.registerListener(new EventListener() {
            @Override
            public void onEvent(Event e) {
                doSomething(e);
            }
        });
    }

    void doSomething(Event e) {
    }

    interface EventSource {
        void registerListener(EventListener e);
    }

    interface EventListener {
        void onEvent(Event e);
    }

    interface Event {
    }
}
