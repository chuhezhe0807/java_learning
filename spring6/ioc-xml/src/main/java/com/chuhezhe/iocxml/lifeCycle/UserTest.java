package com.chuhezhe.iocxml.lifeCycle;

import com.chuhezhe.iocxml.scope.Order;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: UserTest
 * Package: com.chuhezhe.iocxml.lifeCycle
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/17 17:30
 * @Version 1.0
 */
public class UserTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-lifeCycle.xml");

        User user = context.getBean("user", User.class);

        System.out.println("6、bean对象创建完成，可以使用了");

        System.out.println(user);

        context.close(); // 销毁bean
    }
}
