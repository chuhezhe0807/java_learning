package com.chuhezhe.iocxml;

import lombok.Data;

/**
 * ClassName: User
 * Package: com.chuhezhe.iocxml
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/12 22:16
 * @Version 1.0
 */
@Data
public class User {
    private String name;
    private Integer age;

    public void run() {
        System.out.println("run....");
    }
}
