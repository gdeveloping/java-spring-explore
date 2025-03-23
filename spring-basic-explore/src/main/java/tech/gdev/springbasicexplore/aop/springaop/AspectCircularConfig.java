package tech.gdev.springbasicexplore.aop.springaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/3/23 15:59
 */
@Aspect
@Component
public class AspectCircularConfig {
    @Pointcut("execution(* tech.gdev.springbasicexplore.aop.springaop.AspectCircular*.*(..))")
    public void pointcut() {}

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("[SpringAOP][AspectCircularConfig] Before action: " + joinPoint.getSignature().getName());
    }
}
