package tech.gdev.springbasicexplore.aop.springaop;

import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2025/10/5 18:17
 *
 */
@Component
public class AspectArgumentTask {

    public void testArguments1(String str1, String str2) {
        System.out.println("testArguments1: " + str1 + ", " + str2);
    }
}
