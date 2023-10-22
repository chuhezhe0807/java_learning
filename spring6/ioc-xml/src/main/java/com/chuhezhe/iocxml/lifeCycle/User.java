package com.chuhezhe.iocxml.lifeCycle;

/**
 * ClassName: User
 * Package: com.chuhezhe.iocxml.lifeCycle
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/17 17:22
 * @Version 1.0
 */
public class User {
    private String name;

    public User() {
        System.out.println("1、bean对象创建，调用无参构造器");
    }

    // 初始化方法
    public void initMethod() {
        System.out.println("4、bean对象初始化（调用指定初始化方法）");
    }

    // 销毁的方法
    public void destroyMethod() {
        System.out.println("7、bean对象销毁（配置指定的销毁的方法）");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("2、给bean对象设置相关属性");
        this.name = name;
    }
}
