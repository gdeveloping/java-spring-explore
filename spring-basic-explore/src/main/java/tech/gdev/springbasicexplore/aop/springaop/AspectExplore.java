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
        // 验证通过继承接口方式创建的 Advisor
        applicationContext.register(AdvisorBean.class);
        // 验证引入通知
        applicationContext.register(IntroductionAspect.class);
        applicationContext.refresh();

        AspectCircularBeanA aspectCircularBeanA = applicationContext.getBean(AspectCircularBeanA.class);
        AspectCircularBeanB aspectCircularBeanB = applicationContext.getBean(AspectCircularBeanB.class);
        aspectCircularBeanA.aspectMethodA();
        aspectCircularBeanB.aspectMethodB();

        // 验证引入通知
        AspectAction aspectAction = applicationContext.getBean(AspectAction.class);
        if (aspectAction instanceof IntroductionService) {
            ((IntroductionService) aspectAction).service();
        } else {
            System.out.println("not a IntroductionService");
        }

        // 验证 JDK 动态代理
        // 打印内容如下：
        // [SpringAOP][ProxyType] serviceInterface from tech.gdev.springbasicexplore.aop.springaop.AspectOperationJdbProxyImpl
        // [SpringAOP][ProxyType] not a AspectOperationJdbProxyImpl
        AspectOperationJdkProxy aspectOperationJdkProxy = applicationContext.getBean(AspectOperationJdkProxy.class);
        aspectOperationJdkProxy.serviceInterface();
        if (aspectOperationJdkProxy instanceof AspectOperationJdkProxyImpl) {
            ((AspectOperationJdkProxyImpl) aspectOperationJdkProxy).serviceMethod();
        } else {
            System.out.println("[SpringAOP][ProxyType] not a AspectOperationJdbProxyImpl");
        }
        // 验证 CGLIB 动态代理
        // 打印内容如下：
        // [SpringAOP][ProxyType] serviceInterface from tech.gdev.springbasicexplore.aop.springaop.AspectOperationCglibProxyImpl
        // [SpringAOP][ProxyType] serviceMethod from tech.gdev.springbasicexplore.aop.springaop.AspectOperationCglibProxyImpl
        AspectOperationCglibProxy aspectOperationCglibProxy = applicationContext.getBean(AspectOperationCglibProxy.class);
        aspectOperationCglibProxy.serviceInterface();
        if (aspectOperationCglibProxy instanceof AspectOperationCglibProxyImpl) {
            ((AspectOperationCglibProxyImpl) aspectOperationCglibProxy).serviceMethod();
        } else {
            System.out.println("[SpringAOP][ProxyType] not a AspectOperationCglibProxyImpl");
        }

        Thread.sleep(10*1000L);
        applicationContext.close();
    }
}
