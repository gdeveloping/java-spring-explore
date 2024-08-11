package tech.gdev.springbasicexplore.beanlifecycle;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Locale;

/**
 * @author gdev
 * @date 2024/5/18 15:13
 */
@Getter
@Setter
@Slf4j
public class BeanLifeCycle implements InitializingBean, ApplicationContextAware, DisposableBean {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private BeanSingleton beanSingleton;

    @Autowired
    private BeanPrototype beanPrototype;

    @Value("${app.myvara}")
    private String varA;

    public BeanLifeCycle() {
        System.out.println("LifeCycle对象被创建了");
    }

    /**
     * 实现的 Aware 回调接口
     *
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Aware接口起作用，setApplicationContext被调用了，此时user=" + beanSingleton);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("@PostConstruct注解起作用，postConstruct方法被调用了");
        System.out.println("[@PostConstruct] beanSingleton: " + beanSingleton.toString());
        System.out.println("[@PostConstruct] beanPrototype1: " + beanPrototype.toString());
        System.out.println("[@PostConstruct] beanPrototype2: " + beanPrototype.toString());
        System.out.printf("[@PostConstruct] variable in config file, varA: %s%n", varA);
        System.out.println("[@PostConstruct] i18n message, user_label: " + messageSource.getMessage("user_label", null, Locale.SIMPLIFIED_CHINESE));
    }

    /**
     * 实现 InitializingBean 接口
     *
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean接口起作用，afterPropertiesSet方法被调用了");
    }

    /**
     * 通过 {@link Bean#initMethod()}来指定
     *
     */
    public void initMethod() throws Exception {
        System.out.println("@Bean#initMethod()起作用，initMethod方法被调用了");
    }

    @PreDestroy
    public void preDestroy() throws Exception {
        System.out.println("@PreDestroy注解起作用，preDestroy方法被调用了");
    }

    /**
     * 通过 {@link Bean#destroyMethod()}来指定
     *
     */
    public void destroyMethod() throws Exception {
        System.out.println("@Bean#destroyMethod()起作用，destroyMethod方法被调用了");
    }

    /**
     * 实现 DisposableBean 注解
     *
     */
    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean接口起作用，destroy方法被调用了");
    }
}
