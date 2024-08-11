package tech.gdev.springbasicexplore.aop.springaop;

import org.springframework.stereotype.Component;

/**
 * @author gdev
 */
@Component
public class AspectAction {

    /**
     * ***
     * 09:06:58.435 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'loggingAAspectSpringBean'
     * [SpringAOP][LoggingA] Around begin action: testPublic
     * [SpringAOP][LoggingA] Before action: testPublic
     * 09:06:58.437 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'loggingBAspectSpringBean'
     * [SpringAOP][LoggingB] Around begin action: testPublic
     * [SpringAOP][LoggingB] Before action: testPublic
     * testPublic
     * [SpringAOP][LoggingB] AfterReturning action: testPublic
     * [SpringAOP][LoggingB] After action: testPublic
     * [SpringAOP][LoggingB] Around end action: testPublic
     * [SpringAOP][LoggingA] AfterReturning action: testPublic
     * [SpringAOP][LoggingA] After action: testPublic
     * [SpringAOP][LoggingA] Around end action: testPublic
     * ***
     */
    public void testPublic() {
        System.out.println("testPublic");
    }

    protected void testProtected() {
        System.out.println("testProtected");
    }

    private void testPrivate() {
        System.out.println("testPrivate");
    }

    public void invokePrivateFromPublic() {
        System.out.println("invokePrivateFromPublic");
        testPrivate();
    }
}

