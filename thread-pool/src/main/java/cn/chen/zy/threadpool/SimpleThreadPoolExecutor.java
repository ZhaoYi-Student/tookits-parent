package cn.chen.zy.threadpool;

import cn.chen.zy.ex.ThreadPoolJobFailedException;
import cn.chen.zy.log.LogFactory;
import cn.chen.zy.thread.AtomicThreadFactory;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @see java.util.concurrent.ThreadPoolExecutor
 */
public class SimpleThreadPoolExecutor<T> extends ThreadPoolExecutor {

    /**
     * cpu 核数
     */
    private static final int corePoolSize = Runtime.getRuntime().availableProcessors();

    /**
     * 任务列表
     */
    private final List<CompletableFuture<T>> jobList = new ArrayList<>();

    /**
     * 日志
     */
    private static final Logger log = LogFactory.getLogFactory(SimpleThreadPoolExecutor.class);

    public SimpleThreadPoolExecutor(long keepAliveTime, TimeUnit unit, ThreadFactory threadFactory) {
        this(corePoolSize, corePoolSize * 2, keepAliveTime, unit, new LinkedBlockingQueue<>(3000), threadFactory);
    }

    public SimpleThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, new AbortPolicy());
    }

    public SimpleThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new AtomicThreadFactory(), handler);
    }

    private SimpleThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    /**
     * 任务执行
     *
     * @param job 具体任务
     */
    public void execute(Supplier<T> job) {
        CompletableFuture<T> jobFuture = CompletableFuture.supplyAsync(job, this)
                .whenCompleteAsync((r, ex) ->
                    log.info("{} - execute success : {}", Thread.currentThread().getName(), r), this)
                .exceptionallyAsync(ex -> {
                    log.error("{} - execute failed : {}", Thread.currentThread().getName(), ex.getMessage());
                    throw new ThreadPoolJobFailedException(ex.getMessage());
                }, this);
        jobList.add(jobFuture);
    }

    /**
     * 等待所有任务执行完成
     */
    public void waitAllDone() {
        CompletableFuture.allOf(jobList.toArray(new CompletableFuture[0])).join();
    }

}
