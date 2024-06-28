package com.chuhezhe.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * ClassName: User
 * Package: com.chuhezhe.bean
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/18 21:59
 * @Version 1.0
 */
@Component(value = "myUser") // 开启组件扫描后等同于 <bean id="user" class=""></bean> 使用@Component注解时注意需要提供空参构造
//@Repository
//@Service
//@Controller 以上三个注解都可以实现bean对象创建
public class User {
    private String name;
    private int age;

    public User() {}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
