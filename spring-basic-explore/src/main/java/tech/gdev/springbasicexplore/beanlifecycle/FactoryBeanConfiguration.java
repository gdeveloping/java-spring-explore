package tech.gdev.springbasicexplore.beanlifecycle;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gdev
 * @date 2025/3/23 08:57
 */
@Configuration
public class FactoryBeanConfiguration {
    @Bean
    public FactoryBeanA factoryBeanA() {
        return new FactoryBeanA();
    }

    @Bean
    public FactoryBeanB factoryBeanB() {
        return new FactoryBeanB();
    }

    @Bean
    public FactoryBeanTarget factoryBeanTarget() {
        factoryBeanA();
        factoryBeanB();
        return new FactoryBeanTarget();
    }

    public class FactoryBeanTarget {
        @Getter
        @Setter
        private int id = 0;
    }

    public class FactoryBeanA implements FactoryBean {
        private final FactoryBeanTarget target = new FactoryBeanTarget();

        public Object getObject() throws Exception {
            return target;
        }

        public Class<?> getObjectType() {
            return FactoryBeanTarget.class;
        }
    }

    public class FactoryBeanB implements SmartFactoryBean {
        private final FactoryBeanTarget target = new FactoryBeanTarget();

        public Object getObject() throws Exception {
            return target;
        }

        public Class<?> getObjectType() {
            return FactoryBeanTarget.class;
        }

        public boolean isSingleton() {
            return true;
        }

        public boolean isPrototype() {
            return false;
        }

        public boolean isEagerInit() {
            return true;
        }
    }
}
