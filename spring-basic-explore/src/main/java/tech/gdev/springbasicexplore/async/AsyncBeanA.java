package tech.gdev.springbasicexplore.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/3/19 21:35
 */
@Component
@Order(10)
public class AsyncBeanA {
    @Autowired
    private AsyncBeanB asyncBeanB;

    @Async
    public void asyncMethodA() {
        System.out.println("AsyncBeanA.asyncMethodA. " + asyncBeanB.toString());
    }
}
