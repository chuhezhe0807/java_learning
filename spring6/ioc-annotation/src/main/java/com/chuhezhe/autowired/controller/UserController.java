package com.chuhezhe.autowired.controller;

import com.chuhezhe.autowired.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * ClassName: UserController
 * Package: com.chuhezhe.autowired.controller
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/18 22:12
 * @Version 1.0
 */
@Controller
public class UserController {
    // 注入service
//    @Autowired // 根据类型找到对应实现类的对象，完成注入 变量注入方式不能有效地指明依赖，最好使用set注入或构造器注入
//    private UserService userService;

    // set方式注入
//    private UserService userService;
//
//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    // 构造器注入
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void add() {
        System.out.println("controller....");
        userService.add();
    }
}
