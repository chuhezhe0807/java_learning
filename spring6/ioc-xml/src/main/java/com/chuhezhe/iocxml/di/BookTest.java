package com.chuhezhe.iocxml.di;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: BookTest
 * Package: com.chuhezhe.iocxml.di
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/13 21:11
 * @Version 1.0
 */
public class BookTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");

        Book book = context.getBean("book", Book.class);
        System.out.println(book);
    }

    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");

        Book book = context.getBean("bookCon", Book.class);
        System.out.println(book);
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-diref.xml");

        Book book = context.getBean("book", Book.class);
        System.out.println(book);
        System.out.println("");
        book.run();
    }

    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-diref.xml");

        Book book = context.getBean("bookP", Book.class);
        System.out.println(book);
        System.out.println("");
        book.run();
    }
}
