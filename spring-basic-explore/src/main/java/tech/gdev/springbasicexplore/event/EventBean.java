package tech.gdev.springbasicexplore.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author gdev
 * @date 2024/8/4 09:49
 */
public class EventBean extends ApplicationEvent {
    public EventBean(Object source) {
        super(source);
    }
}
