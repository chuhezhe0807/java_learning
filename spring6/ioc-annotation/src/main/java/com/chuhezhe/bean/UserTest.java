package com.chuhezhe.bean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * ClassName: UserTest
 * Package: com.chuhezhe.bean
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/18 22:00
 * @Version 1.0
 */
public class UserTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        User user = context.getBean("user", User.class);
        System.out.println(user);
    }

    @Test
    public void test01() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

        // 使用 @ComponentScan 配合 @Component 注解 向IOC容器中添加组件时，需要 @Component 注解下的类提供空参构造
        // @Repository 注解标注的和 UserExcludeByAssignableType.class 类(子类和实现类都会被排除)被 @ComponentScan 的排除规则排除了，没有被添加到IOC容器中
        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println); // beanConfig myUser
    }

    // 测试 @Lazy 注解
    @Test
    public void test02() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        System.out.println("容器创建完成");
        User user = context.getBean(User.class);

        System.out.println(user);
    }
}
