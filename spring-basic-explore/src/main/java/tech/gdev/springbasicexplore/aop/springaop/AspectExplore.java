package tech.gdev.springbasicexplore.aop.springaop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author gdev
 * @date 2025/3/23 16:11
 */
public class AspectExplore {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("tech.gdev.springbasicexplore.aop.springaop");
        applicationContext.refresh();

        AspectCircularBeanA aspectCircularBeanA = applicationContext.getBean(AspectCircularBeanA.class);
        AspectCircularBeanB aspectCircularBeanB = applicationContext.getBean(AspectCircularBeanB.class);
        aspectCircularBeanA.aspectMethodA();
        aspectCircularBeanB.aspectMethodB();

        AspectAction aspectAction = applicationContext.getBean(AspectAction.class);
        if (aspectAction instanceof IntroductionService) {
            ((IntroductionService) aspectAction).service();
        } else {
            System.out.println("not a IntroductionService");
        }

        Thread.sleep(10*1000L);
        applicationContext.close();
    }
}
