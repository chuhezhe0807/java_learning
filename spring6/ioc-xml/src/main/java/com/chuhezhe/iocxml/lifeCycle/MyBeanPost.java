package com.chuhezhe.iocxml.lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * ClassName: MyBeanPost
 * Package: com.chuhezhe.iocxml.lifeCycle
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/17 17:41
 * @Version 1.0
 */
public class MyBeanPost implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("3、bean对象后置处理器（初始化之前）");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        System.out.println("5、bean对象后置处理器（初始化之之后）");
        return bean;
    }
}
