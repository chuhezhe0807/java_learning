package com.chuhezhe.autowired;

import com.chuhezhe.autowired.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: UserControllerTest
 * Package: com.chuhezhe.autowired
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/18 22:34
 * @Version 1.0
 */
public class UserControllerTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        UserController userController = context.getBean(UserController.class);
        userController.add();
    }
}
