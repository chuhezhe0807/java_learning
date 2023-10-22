package com.chuhezhe.iocxml.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: OrderTest
 * Package: com.chuhezhe.iocxml.scope
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/17 17:02
 * @Version 1.0
 */
public class OrderTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-scope.xml");

        Order order = context.getBean("order", Order.class);
        Order order1 = context.getBean("order", Order.class);

        System.out.println(order == order1);
    }
}
