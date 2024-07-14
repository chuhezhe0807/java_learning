package com.chuhezhe.webapplication.controller;

import com.chuhezhe.webapplication.exception.UserNotExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: UserController
 * Package: com.chuhezhe.webapplication.controller
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/13 22:25
 * @Version 1.0
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/exist/{id:\\d+}")
    @ResponseBody
    public void get(@PathVariable String id) {
        throw new UserNotExistException(id);
    }

    @GetMapping("/get/{id:\\d+}")
    @ResponseBody
    public void getUserId(@PathVariable String id) {
        logger.info("get 请求的 id: {}", id);
    }

    @GetMapping("/exception/{id:\\d+}")
    @ResponseBody
    public void getExceptionUserId(@PathVariable String id) {
        throw new RuntimeException("请求的id不存在。。。");
    }

    @GetMapping("/empty/{id:\\d+}")
    public String getEmpty(@PathVariable String id) {
        return "Empty";
    }
}
