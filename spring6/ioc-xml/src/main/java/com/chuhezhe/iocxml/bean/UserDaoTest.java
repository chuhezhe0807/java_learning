package com.chuhezhe.iocxml.bean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ClassName: UserDaoTest
 * Package: com.chuhezhe.iocxml.bean
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/12 23:31
 * @Version 1.0
 */
public class UserDaoTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        // 根据类型获取接口对应bean 需要bean唯一性，即该接口只能有一个实现类
        UserDao userDao1 = context.getBean(UserDao.class);
        System.out.println(userDao1);
        userDao1.run();
    }
}
