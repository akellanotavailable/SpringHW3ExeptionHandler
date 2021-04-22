package com.epam.expositions.service.impl;

import com.epam.expositions.annotation.Timed;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> currentBean = bean.getClass();
        if (currentBean.isAnnotationPresent(Timed.class)) {
            return Proxy.newProxyInstance(currentBean.getClassLoader(), currentBean.getInterfaces(),
                    (proxy, method, args) -> {
                        long start = System.nanoTime();
                        Object result = method.invoke(bean, args);
                        long elapsed = System.nanoTime() - start;

                        System.out.println("Executing " + method.getName() + " finished in " + elapsed + " ns");

                        return result;
                    });
        } else {
            return bean;
        }
    }
}