package com.chuhezhe.iocxml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: UserTest
 * Package: com.chuhezhe.iocxml
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/12 22:29
 * @Version 1.0
 */
public class UserTest {

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        // 1、根据id获取bean
        User user1 = (User) context.getBean("user");
        System.out.println("id +++" + user1);

        // 2、根据类型获取bean
        User user2 = (User) context.getBean(User.class);
        System.out.println("类型 +++" + user2);

        // 3、根据id和类型
        User user3 = context.getBean("user", User.class);
        System.out.println("id 类型 +++ " + user3);
    }
}
