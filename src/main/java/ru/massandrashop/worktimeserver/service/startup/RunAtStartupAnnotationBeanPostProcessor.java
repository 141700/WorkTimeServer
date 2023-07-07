package ru.massandrashop.worktimeserver.service.startup;

import lombok.SneakyThrows;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class RunAtStartupAnnotationBeanPostProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods = AopProxyUtils.ultimateTargetClass(bean).getDeclaredMethods();
        Arrays.stream(methods)
                .filter(method -> method.getAnnotationsByType(RunAtStartup.class).length > 0)
                .forEach(method -> invokeMethod(bean, method));
        return bean;
    }

    @SneakyThrows
    private void invokeMethod(Object bean, Method method) {
        method.invoke(bean);
    }

}
