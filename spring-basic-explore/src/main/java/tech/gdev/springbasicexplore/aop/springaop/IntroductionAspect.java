package tech.gdev.springbasicexplore.aop.springaop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * 仅在 {@link AspectExplore} 中测试使用，未使用 @Component 修饰。
 * @author gdev
 * @date 2025/5/15 23:22
 */
@Aspect
public class IntroductionAspect {
    @DeclareParents(value = "tech.gdev.springbasicexplore.aop.springaop.AspectAction+",
            defaultImpl = IntroductionServiceImpl.class)
    public IntroductionService introductionService;
}
