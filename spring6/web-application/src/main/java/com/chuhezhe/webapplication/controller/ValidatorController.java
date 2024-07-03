package com.chuhezhe.webapplication.controller;

import com.chuhezhe.webapplication.domain.UserDTO;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ValidatorController
 * Package: com.chuhezhe.webapplication.controller
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/1 22:34
 * @Version 1.0
 */
@RestController
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

    @GetMapping("test2")
    public String test2(@Valid UserDTO user) {
        return "success";
    }
}
