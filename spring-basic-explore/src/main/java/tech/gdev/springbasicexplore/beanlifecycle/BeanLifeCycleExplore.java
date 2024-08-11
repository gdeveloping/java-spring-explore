package tech.gdev.springbasicexplore.beanlifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author gdev
 * @date 2024/5/18 15:12
 */
public class BeanLifeCycleExplore {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanConfiguration.class);
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext();
   }
}
