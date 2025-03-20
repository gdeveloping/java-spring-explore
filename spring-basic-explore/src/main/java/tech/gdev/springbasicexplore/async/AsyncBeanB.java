package tech.gdev.springbasicexplore.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/3/19 21:36
 */
@Component
@Order(20)
public class AsyncBeanB {
    @Autowired
    @Lazy
    private AsyncBeanA asyncBeanA;

    @Async
    public void asyncMethodB() {
        System.out.println("AsyncBeanB.asyncMethodB. " + asyncBeanA.toString());
    }
}
