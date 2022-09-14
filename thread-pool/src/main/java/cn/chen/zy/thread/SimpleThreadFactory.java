package cn.chen.zy.thread;

import java.util.concurrent.ThreadFactory;

public abstract class SimpleThreadFactory implements ThreadFactory {

    private final ThreadGroup group;
    private final String namePrefix;

    public SimpleThreadFactory(String namePrefix) {
        this(Thread.currentThread().getThreadGroup(), namePrefix);
    }

    public SimpleThreadFactory(ThreadGroup group, String namePrefix) {
        this.group = group;
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + getThreadNumber(),
                0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }

    protected abstract int getThreadNumber();

}
