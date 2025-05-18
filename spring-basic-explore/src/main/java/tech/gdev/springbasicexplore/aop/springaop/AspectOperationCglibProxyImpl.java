package tech.gdev.springbasicexplore.aop.springaop;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * 通过 @Scope 注解强制使用 CGLIB 代理。
 *
 * @author gdev
 * @date 2025/5/18 15:06
 */
@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AspectOperationCglibProxyImpl implements AspectOperationCglibProxy {
    @Override
    public void serviceInterface() {
        System.out.println("[SpringAOP][ProxyType] serviceInterface from " + this.getClass().getName());
    }

    public void serviceMethod() {
        System.out.println("[SpringAOP][ProxyType] serviceMethod from " + this.getClass().getName());
    }
}
