package tech.gdev.springbasicexplore.aop.springaop;

/**
 * @author gdev
 * @date 2025/5/15 23:22
 */
public class IntroductionServiceImpl implements IntroductionService {
    @Override
    public void service() {
        System.out.println("[SpringAOP][IntroductionAdvice] IntroductionServiceImpl#service");
    }
}
