package com.chuhezhe.resource;

import com.chuhezhe.config.SpringConfig;
import com.chuhezhe.resource.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
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
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserController userController = context.getBean(UserController.class);
        userController.add();
    }
}
