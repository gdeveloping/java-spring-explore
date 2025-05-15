package tech.gdev.springbasicexplore.aop.springaop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/5/15 23:22
 */
@Component
@Aspect
public class IntroductionAspect {
    @DeclareParents(value = "tech.gdev.springbasicexplore.aop.springaop.AspectAction+",
            defaultImpl = IntroductionServiceImpl.class)
    public IntroductionService introductionService;
}
