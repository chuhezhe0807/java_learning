package com.chuhezhe.bean;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * ClassName: User
 * Package: com.chuhezhe.bean
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/18 21:59
 * @Version 1.0
 */
@Component(value = "user") // 开启组件扫描后等同于 <bean id="user" class=""></bean>
//@Repository
//@Service
//@Controller 以上三个注解都可以实现bean对象创建
public class User {
}
