package tech.gdev.springbasicexplore;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import tech.gdev.springbasicexplore.async.AsyncBean;
import tech.gdev.springbasicexplore.beanlifecycle.BeanLifeCycle;
import tech.gdev.springbasicexplore.event.EventBean;
import tech.gdev.springbasicexplore.transaction.TransactionBean;

@Configuration
@ComponentScan(basePackages = "tech.gdev.springbasicexplore",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = SpringBootApplication.class))
@PropertySource("classpath:application.properties")
public class AppCodeExplore {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppCodeExplore.class);

        BeanLifeCycle beanLifeCycle = context.getBean(BeanLifeCycle.class);

        AsyncBean asyncBean = context.getBean(AsyncBean.class);
        asyncBean.async();

        TransactionBean transactionBean = context.getBean(TransactionBean.class);
        transactionBean.transaction();

        EventBean eventBean = new EventBean(beanLifeCycle);
        context.publishEvent(eventBean);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("i18n/message");
        return messageSource;
    }
}
