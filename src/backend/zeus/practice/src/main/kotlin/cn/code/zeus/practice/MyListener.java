package cn.code.zeus.practice;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        //...
    }

    @EventListener(classes = ApplicationEvent.class)
    public void listen(ApplicationEvent event) {
        //..
    }
}
