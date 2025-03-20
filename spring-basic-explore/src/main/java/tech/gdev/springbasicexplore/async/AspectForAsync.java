package tech.gdev.springbasicexplore.async;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/3/19 21:40
 */
@Aspect
@Component
public class AspectForAsync {
    @Pointcut("execution(* tech.gdev.springbasicexplore.async.AsyncBean*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public void logAroundForAsync(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[SpringAOP][AspectForAsync] Around Before action: " + joinPoint.getSignature().getName());
        joinPoint.proceed();
        System.out.println("[SpringAOP][AspectForAsync] Around After action: " + joinPoint.getSignature().getName());
    }
}
