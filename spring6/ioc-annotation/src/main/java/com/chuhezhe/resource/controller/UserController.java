package com.chuhezhe.resource.controller;

import com.chuhezhe.resource.service.UserService;
import jakarta.annotation.Resource;
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
@Controller("myUserController")
public class UserController {
    @Resource(name = "myUserService") // 根据名称进行注入
    private UserService userService;

    public void add() {
        System.out.println("controller....");
        userService.add();
    }
}
