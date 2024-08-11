package tech.gdev.springbasicexplore.beanlifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gdev
 * @date 2024/5/18 15:17
 */
@Configuration
public class BeanConfiguration {
    @Bean(initMethod = "initMethod", destroyMethod = "destroyMethod")
    public BeanLifeCycle beanLifeCycle() {
        return new BeanLifeCycle();
    }
}
