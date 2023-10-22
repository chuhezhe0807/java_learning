package com.chuhezhe.iocxml.auto.controller;

import com.chuhezhe.iocxml.auto.service.UserService;
import com.chuhezhe.iocxml.auto.service.UserServiceImpl;

/**
 * ClassName: UserController
 * Package: com.chuhezhe.iocxml.auto.controller
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/17 21:25
 * @Version 1.0
 */
public class UserController {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void addUser() {
        System.out.println("controller execute....");

        // 调用service中的方法
        userService.addUserService();
    }
}
