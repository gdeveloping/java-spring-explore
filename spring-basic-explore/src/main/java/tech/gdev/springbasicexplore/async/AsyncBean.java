package tech.gdev.springbasicexplore.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2024/5/19 14:30
 */
@Component
public class AsyncBean {
    @Async
    public void async() {
        System.out.println("async start");
        try {
            Thread.sleep(10*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("async finish");
    }
}
