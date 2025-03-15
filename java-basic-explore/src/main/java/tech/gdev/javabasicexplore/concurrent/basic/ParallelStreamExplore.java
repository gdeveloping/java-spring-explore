package tech.gdev.javabasicexplore.concurrent.basic;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author gdev
 * @date 2025/3/14 15:00
 */
public class ParallelStreamExplore {
    public static void main(String[] args) throws Exception {
        parallelStreamExploreThreadPool();
    }

    public static void parallelStreamExploreBasicUsage() {
        List<String> list = Arrays.asList("apple", "banana", "orange", "grapefruit", "kiwi");
        list.parallelStream()
                .filter(s -> s.length() <= 5)
                .forEach(System.out::println);

        int sum = IntStream.rangeClosed(1, 100)
                .parallel()
                .reduce(0, Integer::sum);
        System.out.println(sum);
    }


    public static void parallelStreamExploreThreadPool() throws Exception {
        int cpu = Runtime.getRuntime().availableProcessors();
        System.out.println("cpu count: " + cpu);

        List<Integer> list = IntStream.rangeClosed(1, 2000).boxed().collect(Collectors.toList());

        // ForkJoinPool.commonPool 的状态无法在运行时修改，为了自定义 ForkJoinPool.commonPool 配置，如下三个方法应当分别启动。
        // 使用默认 ForkJoinPool.commonPool
//        useDefaultThreadPool(list);

        // 使用自定义 ForkJoinPool.commonPool
        useCustomizedThreadPool(list);

        // 自定义 ForkJoinPool.commonPool 线程数量
        useThreadPoolWithSystemConfig(list);
    }


    private static void useDefaultThreadPool(List<Integer> list) {
        beforeParallelStream();
        list.parallelStream().forEach(ParallelStreamExplore::recordThreadName);
        showThreadInfo();
    }

    private static void useCustomizedThreadPool(List<Integer> list) throws InterruptedException, ExecutionException {
        beforeParallelStream();
        ForkJoinPool pool = new ForkJoinPool(2);
        ForkJoinTask<?> futureTask = pool.submit(() -> list.parallelStream().forEach(ParallelStreamExplore::recordThreadName));
        futureTask.get();
        afterParallelStream();
    }

    private static void useThreadPoolWithSystemConfig(List<Integer> list) throws Exception {
        beforeParallelStream();
        // 配置 ForkJoinPool 线程池数量。无法在运行时修改线程数量，只能在 ForkJoinPool.commonPool 创建前配置。
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "5");
        list.parallelStream().forEach(ParallelStreamExplore::recordThreadName);
        afterParallelStream();
    }

    private static final ConcurrentSkipListSet<String> THREAD_NAME_SET = new ConcurrentSkipListSet<>();

    private static void recordThreadName(Integer num) {
        THREAD_NAME_SET.add(Thread.currentThread().getName());
    }

    private static void beforeParallelStream() {
        THREAD_NAME_SET.clear();
        System.out.println();
    }

    private static void afterParallelStream() {
        showThreadInfo();
        THREAD_NAME_SET.clear();
    }

    private static void showThreadInfo() {
        System.out.println("ForkJoinPool thread count: " + ForkJoinPool.commonPool().getPoolSize());
        System.out.println("actual thread size: " + THREAD_NAME_SET.size());
        List<String> lst = THREAD_NAME_SET.stream().sorted().collect(Collectors.toList());
        System.out.println("thread name example 1: " + lst.get(0));
        System.out.println("thread name example 2: " + lst.get(lst.size() - 1));
    }
}
