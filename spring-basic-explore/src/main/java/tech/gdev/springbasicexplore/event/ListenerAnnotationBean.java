package tech.gdev.springbasicexplore.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author gdev
 * @date 2024/8/4 09:53
 */
@Component
public class ListenerAnnotationBean {
    @EventListener
    public void listen(EventBean bean) {
        System.out.printf("listen EventBean. class:%s; method: listen.%n", this.getClass());
    }

    @EventListener
    @Async
    public void listenAsync(EventBean bean) {
        System.out.printf("listen EventBean. class:%s; method: listenAsync.%n", this.getClass());
    }
}
