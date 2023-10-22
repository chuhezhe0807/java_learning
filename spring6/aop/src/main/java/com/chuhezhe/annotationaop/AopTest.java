package com.chuhezhe.annotationaop;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: AopTest
 * Package: com.chuhezhe.annotationaop
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/24 23:37
 * @Version 1.0
 */
public class AopTest {
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        Calculator calculator = context.getBean(Calculator.class);
//        calculator.add(1, 2);
        calculator.mul(1, 2);
    }
}
