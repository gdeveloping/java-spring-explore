package tech.gdev.springbasicexplore.async;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author gdev
 * @date 2024/5/19 14:29
 */
public class AsyncExplore {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("tech.gdev.springbasicexplore.async");
        AsyncBean asyncBean = applicationContext.getBean(AsyncBean.class);
        asyncBean.async();
        applicationContext.close();
    }
}
