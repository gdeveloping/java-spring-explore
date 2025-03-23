package tech.gdev.springbasicexplore.applicationrunner;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/3/23 16:04
 */
@Component
public class ApplicationRunnerBean implements ApplicationRunner, ApplicationContextAware {
    private ApplicationContext applicationContext;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("ApplicationRunnerBean");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
