package com.chuhezhe.webapplication.controller;

import com.chuhezhe.webapplication.entity.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * ClassName: ValidatorController
 * Package: com.chuhezhe.webapplication.controller
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/1 22:34
 * @Version 1.0
 */
@RestController // @Controller 和 @ResponseBody 的组合，@ResponseBody 将方法的返回值直接写入HTTP响应实体中，而不是将返回值解释为视图名称(HTML的名称)，然后再根据视图名称去寻找对应的视图模板
@Validated
public class ValidatorController {

    /**
     * name 参数使用 @NotNull 注解标注，标识其不可为空
     *         使用 @Length 注解标注，表示其长度应该在3到5之间
     *
     * 要使以上注解生效，还需要在类上加上 @Validated 注解
     */
    @GetMapping("test1")
    public String test1(@Length(min = 3, max = 5, message = "自定义 message，参数长度需要在3到5之间") String name) {
        return "success";
    }

    @PostMapping("test2") // @RequestBody 主要用来接收前端传递给后端的json字符串中的数据的（请求体中的数据）
    public String test2(@Valid @RequestBody User user) {
        return "success";
    }

    // 分组校验 保存
    @PostMapping("test3")
    public String test3(@Validated(User.Save.class) @RequestBody User user) {
        return "success";
    }

    // 分组校验 更新
    @PostMapping("test4")
    public String test4(@Validated(User.Update.class) @RequestBody User user) {
        return "success";
    }

    // 嵌套校验
    @PostMapping("test5")
    public String test5(@Validated @RequestBody User user) {
        return "success";
    }
}
