package tech.gdev.javabasicexplore.concurrent.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author gdev
 * @date 2024/8/24 11:06
 */
public class ConcurrentBasicExplore {
    public static void main(String[] args) {
        ConcurrentBasicExplore instance = new ConcurrentBasicExplore();
        instance.exploreFutureCancel();
    }

    public void exploreCompletableFutureTimeout() {

    }

    public void exploreFutureCancel() {
        int futureTaskSleepTime = 1 * 1000;
        int mainTaskSleepTime = 1 * 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        FutureTask futureTaskBlock = new FutureTask<>(() -> {
            System.out.println("begin sleep in future task in " + Thread.currentThread().getName());
            while (true) {
                try {
                    long num = 0;
                    for (long i = 0; i < Integer.MAX_VALUE; i++) {
                        num++;
                        num--;
                    }
                    System.out.println("isInterrupted: " + Thread.currentThread().isInterrupted() + " in " + Thread.currentThread().getName());
                } catch (Exception e) {
                    System.out.println("catch InterruptedException in " + Thread.currentThread().getName());
                    System.out.println("isInterrupted when catch InterruptedException: " + Thread.currentThread().isInterrupted() + " in " + Thread.currentThread().getName());
                }
            }
        });

        FutureTask futureTaskSleep = new FutureTask<>(() -> {
            System.out.println("begin sleep in future task in " + Thread.currentThread().getName());
            while (true) {
                try {
                    System.out.println("isInterrupted: " + Thread.currentThread().isInterrupted() + " in " + Thread.currentThread().getName());
                    Thread.sleep(futureTaskSleepTime);
                } catch (InterruptedException e) {
                    // 在Java中，InterruptedException是一个特殊的异常，它通常由线程在等待、休眠或者接受某些阻塞操作时被中断时抛出。
                    // 当线程捕获到InterruptedException时，Java虚拟机会自动清除当前线程的中断状态。
                    // 这意味着，一旦捕获了这个异常，如果再调用Thread.currentThread().isInterrupted()，它将返回false，因为中断状态已经被清除了。
                    System.out.println("catch InterruptedException in " + Thread.currentThread().getName());
                    System.out.println("isInterrupted when catch InterruptedException: " + Thread.currentThread().isInterrupted() + " in " + Thread.currentThread().getName());
                }
            }
        });

        /**
         * 仅执行 futureTaskBlock 时的日志
         * begin sleep in future task in pool-1-thread-1
         * isInterrupted: false in pool-1-thread-1
         * Now to cancel future task from main
         * cancel result: true
         * isCancelled: true
         * isDone: true
         * isInterrupted: true in pool-1-thread-1
         * isInterrupted: true in pool-1-thread-1
         * isInterrupted: true in pool-1-thread-1
         * isInterrupted: true in pool-1-thread-1
         * isInterrupted: true in pool-1-thread-1
         * ...
         * 永久运行下去
         */
        executorService.submit(futureTaskBlock);

        /**
         * 仅执行 futureTaskSleep 时的日志
         * begin sleep in future task in pool-1-thread-1
         * isInterrupted: false in pool-1-thread-1
         * Now to cancel future task from main
         * cancel result: true
         * isCancelled: true
         * isDone: true
         * isInterrupted: false in pool-1-thread-1
         * isInterrupted: false in pool-1-thread-1
         * isInterrupted: false in pool-1-thread-1
         * isInterrupted: false in pool-1-thread-1
         * isInterrupted: false in pool-1-thread-1
         * isInterrupted: false in pool-1-thread-1
         * isInterrupted: false in pool-1-thread-1
         * ...
         * 永久运行下去
         */
//        executorService.submit(futureTaskSleep);

        // 等待线程池启动
        try {
            Thread.sleep(mainTaskSleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 查询结果
        System.out.println("Now to cancel future task from " + Thread.currentThread().getName());
        boolean result = futureTaskBlock.cancel(true);
        System.out.println("cancel result: " + result);
        System.out.println("isCancelled: " + futureTaskBlock.isCancelled());
        System.out.println("isDone: " + futureTaskBlock.isDone());

        // 此时线程池被占用，无法启动新的线程
        executorService.submit(() -> {
            System.out.println("I'm another task in " + Thread.currentThread().getName());
        });
    }


}
