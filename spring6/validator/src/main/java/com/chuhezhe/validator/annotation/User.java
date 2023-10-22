package com.chuhezhe.validator.annotation;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * ClassName: User
 * Package: com.chuhezhe.validator.annotation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 23:28
 * @Version 1.0
 */
public class User {
    @NotBlank
    private String name;
    @Min(0)
    @Max(150)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
