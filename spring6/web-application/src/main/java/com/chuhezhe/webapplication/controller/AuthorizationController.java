package com.chuhezhe.webapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: AuthorizationController
 * Package: com.chuhezhe.webapplication.controller
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/18 23:12
 * @Version 1.0
 */
@Controller
@RequestMapping("/auth")
public class AuthorizationController {

    @GetMapping ("/admin/api")
    public String admin() {
        return "auth/admin";
    }

    @GetMapping ("/user/api")
    public String user() {
        return "auth/user";
    }

    @GetMapping ("/user/api/a")
    @ResponseBody
    public String userSubs() {
        return "auth/user/a";
    }

    @GetMapping ("/app/api")
    public String app() {
        return "auth/app";
    }

    @GetMapping ("/noAuth")
    public String noAuth() {
        return "auth/noAuth";
    }
}
