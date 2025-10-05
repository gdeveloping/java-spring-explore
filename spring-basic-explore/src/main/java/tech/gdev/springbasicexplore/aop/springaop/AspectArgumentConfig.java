package tech.gdev.springbasicexplore.aop.springaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/10/5 18:20
 *
 */
@Aspect
@Component
public class AspectArgumentConfig {

    @Before(value = "execution(* tech.gdev.springbasicexplore.aop.springaop.AspectArgumentTask.*(..)) && args(str1, str2)", argNames = "joinPoint,str1,str2")
    public void logBefore1(JoinPoint joinPoint, String str1, String str2) {
        System.out.print("[SpringAOP][AspectArgumentConfig] Before action: " + joinPoint.getSignature().getName());
        System.out.print("; Argument1: " + str1);
        System.out.print("; Argument2: " + str2);
        System.out.println();
    }

    @Before(value = "execution(* tech.gdev.springbasicexplore.aop.springaop.AspectArgumentTask.*(..)) && args(str1, str2)")
    public void logBefore2(JoinPoint joinPoint, String str1, String str2) {
        System.out.print("[SpringAOP][AspectArgumentConfig] Before action: " + joinPoint.getSignature().getName());
        System.out.print("; Argument1: " + str1);
        System.out.print("; Argument2: " + str2);
        System.out.println();
    }
}
