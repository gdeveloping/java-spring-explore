package tech.gdev.springbasicexplore.aop.springaop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * 仅在 {@link AspectExplore} 中测试使用，未使用 @Component 修饰。
 * @author gdev
 * @date 2025/5/13 22:55
 */
public class AdvisorBean implements Advisor {
    private static MethodBeforeAdvice methodBeforeAdvice = new MethodBeforeAdvice() {
        @Override
        public void before(Method method, Object[] args, Object target) throws Throwable {
            System.out.println("[SpringAOP][AdvisorBean] Before advice AdvisorBean for method: " + method.getName());
        }
    };

    @Override
    public Advice getAdvice() {
        return methodBeforeAdvice;
    }

    /**
     * Note that this method is not currently used by the framework. Typical Advisor implementations always return true.
     * 请注意，当前框架并未使用此方法。典型的`Advisor`实现总是返回`true`。
     */
    @Override
    public boolean isPerInstance() {
        return true;
    }
}
