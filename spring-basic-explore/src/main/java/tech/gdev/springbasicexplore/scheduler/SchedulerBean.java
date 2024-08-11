package tech.gdev.springbasicexplore.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2024/8/3 16:40
 */
@Component
public class SchedulerBean {
    private long count = 1L;

    /**
     * 每隔 1min 执行一次
     */
    @Scheduled(fixedRate = 60000)
    public void schedule() {
        System.out.println("[schedule] count: " + (count++));
    }
}
