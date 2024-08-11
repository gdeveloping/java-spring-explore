package tech.gdev.springbasicexplore.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2024/8/4 09:48
 */
@Component
public class ListenerBean implements ApplicationListener<EventBean> {

    @Override
    public void onApplicationEvent(EventBean event) {
        System.out.printf("listen EventBean. class:%s; method: onApplicationEvent.%n", this.getClass());
    }
}
