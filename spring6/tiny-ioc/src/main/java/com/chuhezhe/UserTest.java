package com.chuhezhe;

import com.chuhezhe.bean.AnnotationApplicationContext;
import com.chuhezhe.bean.ApplicationContext;
import com.chuhezhe.service.UserService;

/**
 * ClassName: UserTest
 * Package: com.chuhezhe
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/24 13:48
 * @Version 1.0
 */
public class UserTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationApplicationContext("com.chuhezhe");

        UserService userService = (UserService) context.getBean(UserService.class);
        System.out.println(userService);
        userService.add();
    }
}
