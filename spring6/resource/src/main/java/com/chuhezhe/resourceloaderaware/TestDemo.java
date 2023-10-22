package com.chuhezhe.resourceloaderaware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 * ClassName: TestDemo
 * Package: com.chuhezhe.resourceloaderaware
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 18:40
 * @Version 1.0
 */
public class TestDemo {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("bean.xml");

        TestBean testBean = ctx.getBean("testBean", TestBean.class);
        ResourceLoader resourceLoader = testBean.getResourceLoader();

        System.out.println(ctx == resourceLoader); // true
    }
}
