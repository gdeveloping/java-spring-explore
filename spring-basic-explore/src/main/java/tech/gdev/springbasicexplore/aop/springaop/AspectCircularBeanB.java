package tech.gdev.springbasicexplore.aop.springaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/3/23 15:54
 */
@Order(20)
@Component
public class AspectCircularBeanB {
    @Autowired
    private AspectCircularBeanA aspectCircularBeanA;

    public void aspectMethodB() {
        System.out.println("AspectCircularBeanB.aspectMethodB. aspectCircularBeanA: " + aspectCircularBeanA.toString());
    }
}
