package com.chuhezhe.validator.annotation;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: UserTest
 * Package: com.chuhezhe.validator.annotation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 23:37
 * @Version 1.0
 */
public class UserTest {
    @Test
    public void test01() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ValidationConfig.class);

        MyValidator01 validation1 = context.getBean(MyValidator01.class);

        User user = new User();
        user.setAge(-1);
        user.setName("xiaozhang");

        System.out.println(validation1.validatorByUserOne(user));
    }

    @Test
    public void test02() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ValidationConfig.class);

        MyValidator02 validation2 = context.getBean(MyValidator02.class);

        User user = new User();
        user.setAge(11);
        user.setName("xiaozhang");

        System.out.println(validation2.validatorByUserTwo(user));
    }
}
