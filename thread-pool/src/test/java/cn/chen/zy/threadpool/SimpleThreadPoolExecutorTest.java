package cn.chen.zy.threadpool;

import cn.chen.zy.log.LogFactory;
import cn.chen.zy.thread.AtomicThreadFactory;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SimpleThreadPoolExecutorTest {

    private final Object lock = new Object();

    private int ticket = 1000;

    private static final Logger log = LogFactory.getLogFactory(SimpleThreadPoolExecutorTest.class);

    @Test
    public void executeTest() {

        AtomicThreadFactory atomicThreadFactory = new AtomicThreadFactory("多线程卖票");

        SimpleThreadPoolExecutor<Integer> threadPoolExecutor
                = new SimpleThreadPoolExecutor<>(0, TimeUnit.MILLISECONDS, atomicThreadFactory);

        IntStream.range(0, 10).forEach(item -> threadPoolExecutor.execute(() -> {
            sealTicket();
            return 0;
        }));
        threadPoolExecutor.waitAllDone();
    }

    private void sealTicket() {
        while (ticket > 0) {
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock) {
                if (ticket > 0) {
                    ticket -= 1;
                    log.info("{} - seal success and decrement , end with have {} tickets", Thread.currentThread().getName(), ticket);
                }
            }
        }
    }
}