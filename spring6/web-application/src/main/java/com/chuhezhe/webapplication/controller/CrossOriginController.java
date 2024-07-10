package com.chuhezhe.webapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: CrossOriginController
 * Package: com.chuhezhe.webapplication.controller
 * Description: Spring 处理跨域
 *
 * @Author Chuhezhe
 * @Create 2024/7/9 22:30
 * @Version 1.0
 */
@Controller
@RequestMapping("/crossorigin")
//@CrossOrigin(value = "*") // value 为指定所有的跨域集合，"*"表示所有域都支持 这些值对应HTTP请求头中的 Access-Control-Allow-Origin
public class CrossOriginController {

    @GetMapping("/test1")
    public String crossOriginIndex() {
        return "CrossOriginIndex";
    }

    /**
     * 这个接口用于测试跨域，CrossOriginIndex.html 中页面加载完成后会发送一个 http://test.chuhezhe.cc:8081/hello get请求
     * 本地hosts文件已经修改映射
     * 127.0.0.1	test.chuhezhe.cc
     * 所以这次请求是跨域的
     */
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
