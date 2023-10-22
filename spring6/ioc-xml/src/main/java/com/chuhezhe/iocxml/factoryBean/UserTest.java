package com.chuhezhe.iocxml.factoryBean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: UserTest
 * Package: com.chuhezhe.iocxml.factoryBean
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/17 21:20
 * @Version 1.0
 */
public class UserTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-factoryBean.xml");
        User user = (User) context.getBean("user");
        System.out.println(user);
    }
}
