package tech.gdev.springbasicexplore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import tech.gdev.springbasicexplore.aop.springaop.AspectAction;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AspectAppTest {

    @Autowired
    private AspectAction aspectAction;

    @MockBean
    private MessageSource messageSource;

    @Test
    public void test1() {
        aspectAction.testPublic();
    }
}
