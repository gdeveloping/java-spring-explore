package tech.gdev.springbasicexplore.aop.springaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/3/23 15:54
 */
@Order(10)
@Component
public class AspectCircularBeanA {
    @Autowired
    private AspectCircularBeanB aspectCircularBeanB;

    public void aspectMethodA() {
        System.out.println("AspectCircularBeanA.aspectMethodA. aspectCircularBeanB: " + aspectCircularBeanB.toString());
    }
}
