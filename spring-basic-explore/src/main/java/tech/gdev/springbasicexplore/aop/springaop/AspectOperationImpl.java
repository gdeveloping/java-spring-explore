package tech.gdev.springbasicexplore.aop.springaop;

import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/5/15 23:25
 */
@Component
public class AspectOperationImpl implements AspectOperation {
    @Override
    public void serviceInterface() {
        System.out.println("serviceInterface");
    }

    public void serviceMethod() {
        System.out.println("serviceMethod");
    }
}
