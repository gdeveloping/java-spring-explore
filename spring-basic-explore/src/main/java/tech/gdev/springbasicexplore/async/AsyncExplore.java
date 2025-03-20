package tech.gdev.springbasicexplore.async;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tech.gdev.springbasicexplore.aop.springaop.AspectConfig;

/**
 * @author gdev
 * @date 2024/5/19 14:29
 */
public class AsyncExplore {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AspectConfig.class);
        applicationContext.scan("tech.gdev.springbasicexplore.async");
        applicationContext.refresh();
        AsyncBean asyncBean = applicationContext.getBean(AsyncBean.class);
        asyncBean.async();
        AsyncBeanA asyncBeanA = applicationContext.getBean(AsyncBeanA.class);
        asyncBeanA.asyncMethodA();
        AsyncBeanB asyncBeanB = applicationContext.getBean(AsyncBeanB.class);
        asyncBeanB.asyncMethodB();
        Thread.sleep(10*1000L);
        applicationContext.close();
    }
}
