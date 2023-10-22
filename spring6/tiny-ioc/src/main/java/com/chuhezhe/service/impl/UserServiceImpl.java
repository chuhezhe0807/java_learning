package com.chuhezhe.service.impl;

import com.chuhezhe.annotation.MyBean;
import com.chuhezhe.annotation.MyDi;
import com.chuhezhe.dao.UserDao;
import com.chuhezhe.service.UserService;

/**
 * ClassName: UserServiceImpl
 * Package: service.impl
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/20 23:45
 * @Version 1.0
 */
@MyBean
public class UserServiceImpl implements UserService {
    @MyDi
    private UserDao userDao;

    @Override
    public void add() {
        System.out.println("service add......");
        // 调用dao里面的方法
        userDao.add();
    }
}
