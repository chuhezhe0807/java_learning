package com.chuhezhe.iocxml.auto;

import com.chuhezhe.iocxml.auto.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: UserTest
 * Package: com.chuhezhe.iocxml.auto
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/17 21:59
 * @Version 1.0
 */
public class UserTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-auto.xml");

        UserController userController = context.getBean("userController", UserController.class);
        userController.addUser();
    }
}
