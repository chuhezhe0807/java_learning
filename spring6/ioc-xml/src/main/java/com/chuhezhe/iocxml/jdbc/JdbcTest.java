package com.chuhezhe.iocxml.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import com.chuhezhe.iocxml.di.Book;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: JdbcTest
 * Package: com.chuhezhe.iocxml.jdbc
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/13 23:37
 * @Version 1.0
 */
public class JdbcTest {
    @Test
    public void test1(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/spring?serverTimezone=UTC");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("root");
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbc.xml");

        DruidDataSource druidDataSource = context.getBean(DruidDataSource.class);
        System.out.println(druidDataSource.getUrl());
    }
}
