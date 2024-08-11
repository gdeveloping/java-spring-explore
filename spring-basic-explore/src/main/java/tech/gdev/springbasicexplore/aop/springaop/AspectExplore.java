package tech.gdev.springbasicexplore.aop.springaop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author gdev
 */
@Component
@Getter
@Setter
public class AspectExplore {

    @Autowired
    private AspectAction aspectAction;

    @PostConstruct
    public void postConstruct() {
        System.out.println("***");
        aspectAction.testPublic();
        System.out.println("***");
        aspectAction.testProtected();
        System.out.println("***");
        aspectAction.invokePrivateFromPublic();
        System.out.println("***");

        // 查看 method-advice map 缓存使用情况
        System.out.println("***");
        aspectAction.testPublic();
    }
}
