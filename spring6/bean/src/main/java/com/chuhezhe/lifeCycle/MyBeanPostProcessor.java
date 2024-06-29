package com.chuhezhe.lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * ClassName: MyBeanPostProcessor
 * Package: com.chuhezhe.lifeCycle
 * Description: Bean 后置通知处理器
 *
 * @Author Chuhezhe
 * @Create 2024/6/29 15:08
 * @Version 1.0
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " 初始化之前调用");

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " 销毁之后调用");

        return bean;
    }
}
