package tech.gdev.springbasicexplore.aop.springaop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

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

        // 打印内容如下：
        // interfaceSet: [interface tech.gdev.springbasicexplore.aop.springaop.AspectOperationJdkProxy]
        // methodsFromInterface: [public abstract void tech.gdev.springbasicexplore.aop.springaop.AspectOperationJdkProxy.serviceInterface()]
        // methodsFromImpl: [public void tech.gdev.springbasicexplore.aop.springaop.AspectOperationJdkProxyImpl.serviceInterface(), public void tech.gdev.springbasicexplore.aop.springaop.AspectOperationJdkProxyImpl.serviceMethod()]
        Set<Class<?>> interfaceSet = ClassUtils.getAllInterfacesForClassAsSet(AspectOperationJdkProxyImpl.class);
        System.out.println("interfaceSet: " + interfaceSet);
        Method[] methodsFromInterface = ReflectionUtils.getAllDeclaredMethods(interfaceSet.iterator().next());
        System.out.println("methodsFromInterface: " + Arrays.deepToString(methodsFromInterface));
        Method[] methodsFromImpl = ReflectionUtils.getAllDeclaredMethods(AspectOperationJdkProxyImpl.class);
        methodsFromImpl = Arrays.stream(methodsFromImpl)
                .filter(item -> item.toString().contains("tech.gdev."))
                .collect(Collectors.toList()).toArray(new Method[0]);
        System.out.println("methodsFromImpl: " + Arrays.deepToString(methodsFromImpl));

        AspectArgumentTask  aspectArgumentTask = applicationContext.getBean(AspectArgumentTask.class);
        aspectArgumentTask.testArguments1("input1", "input2");

        Thread.sleep(10*1000L);
        applicationContext.close();
    }
}
