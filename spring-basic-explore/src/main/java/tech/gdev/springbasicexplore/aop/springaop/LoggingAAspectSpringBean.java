package tech.gdev.springbasicexplore.aop.springaop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 */
@Order(1)
@Aspect
@Component
public class LoggingAAspectSpringBean {

    @Pointcut("execution(* tech.gdev.springbasicexplore.aop.springaop.AspectAction.*(..))")
    public void pointcut() {
    }

    @AfterReturning("pointcut()")
    public void logAfterReturningAction(JoinPoint joinPoint) {
        System.out.println("[SpringAOP][LoggingA] AfterReturning action: " + joinPoint.getSignature().getName());
    }

    @AfterThrowing("pointcut()")
    public void logAfterThrowingAction(JoinPoint joinPoint) {
        System.out.println("[SpringAOP][LoggingA] AfterThrowing action: " + joinPoint.getSignature().getName());
    }

    @Around("pointcut()")
    public void logAroundAction(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("[SpringAOP][LoggingA] Around begin action: " + joinPoint.getSignature().getName());
        joinPoint.proceed();
        System.out.println("[SpringAOP][LoggingA] Around end action: " + joinPoint.getSignature().getName());
    }

    @Before("pointcut()")
    public void logBeforeAction(JoinPoint joinPoint) {
        System.out.println("[SpringAOP][LoggingA] Before action: " + joinPoint.getSignature().getName());
    }

    @After("pointcut()")
    public void logAfterAction(JoinPoint joinPoint) {
        System.out.println("[SpringAOP][LoggingA] After action: " + joinPoint.getSignature().getName());
    }
}