package tech.gdev.springbasicexplore.beanlifecycle;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2024/8/3 17:15
 */
@Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class BeanPrototype {
}
