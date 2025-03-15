package tech.gdev.javabasicexplore.concurrent.basic;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author gdev
 * @date 2025/3/14 18:33
 */
public class CompletableFutureExplore {
    public static void main(String[] args) throws Exception {
        timeoutExplore();
    }

    /**
     * timeout 之后，CompletableFuture 并没有取消运行中的任务，没有设置 Interrupt 标志位。
     *
     * 执行结果如下：
     * I'm running. Status isInterrupted: false in ForkJoinPool.commonPool-worker-19.Time used: 8000.
     * I'm running. Status isInterrupted: false in ForkJoinPool.commonPool-worker-19.Time used: 9000.
     * catch Exception class java.util.concurrent.ExecutionException in main
     * I'm running. Status isInterrupted: false in ForkJoinPool.commonPool-worker-19.Time used: 10000.
     * I'm running. Status isInterrupted: false in ForkJoinPool.commonPool-worker-19.Time used: 11000.
     * I'm running. Status isInterrupted: false in ForkJoinPool.commonPool-worker-19.Time used: 12000.
     *
     * @throws Exception -
     */
    public static void timeoutExplore() throws Exception {
        Runnable runnable = () -> {
            long timeUsed = 0;
            final long timeItem = 1000L;
            while (true) {
                try {
                    System.out.println("I'm running. " +
                            "Status isInterrupted: " + Thread.currentThread().isInterrupted() + " in " + Thread.currentThread().getName() + "." +
                            "Time used: " + timeUsed + ".");
                    Thread.sleep(timeItem);
                    timeUsed += timeItem;
                } catch (InterruptedException e) {
                    System.out.println("catch InterruptedException in " + Thread.currentThread().getName());
                    System.out.println("isInterrupted when catch InterruptedException: " + Thread.currentThread().isInterrupted() + " in " + Thread.currentThread().getName());
                }
            }
        };

        CompletableFuture future = CompletableFuture
                .runAsync(runnable)
                .orTimeout(10, TimeUnit.SECONDS);
//                .completeOnTimeout(null, 10, TimeUnit.SECONDS);
        try {
            future.get();
        } catch (Exception e) {
            System.out.println("catch Exception " + e.getClass() + " in " + Thread.currentThread().getName());
        }
        ForkJoinPool.commonPool().awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("END");
    }
}
