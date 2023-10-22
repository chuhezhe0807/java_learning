package com.chuhezhe.validator.method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * ClassName: UserTest
 * Package: com.chuhezhe.validator.method
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/5 0:09
 * @Version 1.0
 */
public class UserTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ValidationConfig.class);

        MyService service = context.getBean(MyService.class);
        User user = new User();
        user.setName("xiaozhang");
        user.setPhone("13100000000");

        service.testMethod(user);
    }
}
