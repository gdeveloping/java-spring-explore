package tech.gdev.springbasicexplore.aop.springaop;

import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/5/15 23:25
 */
@Component
public class AspectOperationJdkProxyImpl implements AspectOperationJdkProxy {
    @Override
    public void serviceInterface() {
        System.out.println("[SpringAOP][ProxyType] serviceInterface from " + this.getClass().getName());
    }

    public void serviceMethod() {
        System.out.println("[SpringAOP][ProxyType] serviceMethod from " + this.getClass().getName());
    }
}
