package com.chuhezhe.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: BeanLifeCycleTest
 * Package: com.chuhezhe.lifeCycle
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/29 14:54
 * @Version 1.0
 */
public class BeanLifeCycleTest {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);
        System.out.println("容器创建完成");
        User user = context.getBean(User.class); // 如果向IOC容器中纳入User组件时，使用了@Lazy注解，则不会在IOC容器启动的时候创建对象，只有在使用时才会创建

        context.close();
    }
}
