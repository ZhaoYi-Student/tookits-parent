package cn.chen.zy.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicThreadFactory extends SimpleThreadFactory {

    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    public AtomicThreadFactory() {
        super("pool - " + poolNumber.getAndIncrement() + " - thread");
    }

    public AtomicThreadFactory(String namePrefix) {
        super(namePrefix + poolNumber.getAndIncrement() + " - thread");
    }

    @Override
    protected int getThreadNumber() {
        return threadNumber.getAndIncrement();
    }
}
