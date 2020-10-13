package cn.code.zeus.practice.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ContextUtil implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextUtil.context = applicationContext;
    }

    private static ApplicationContext context;

    public static <T> T getByType(Class<T> clzz) {
        return context.getBean(clzz);
    }

    public static <T> Map<String, T> getBeansByType(Class<T> clzz) {
//        这样就能拿到一个接口的所有实现
        return context.getBeansOfType(clzz);
    }

    public static <T> T getByName(String name, Class<T> clzz) {
        return context.getBean(name, clzz);
    }
}
