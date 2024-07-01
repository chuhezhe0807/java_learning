package com.chuhezhe.lifeCycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * ClassName: User
 * Package: com.chuhezhe.lifeCycle
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/29 14:44
 * @Version 1.0
 */
public class User {
    private String name;
    private int age;

    public User() {
        System.out.println("调用无参构造器");
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("有参构造器");
    }

//    @PostConstruct
    public void init() {
        System.out.println("User init.");
    }

//    @PreDestroy
    public void destroy() {
        System.out.println("User destroy.");
    }
}
