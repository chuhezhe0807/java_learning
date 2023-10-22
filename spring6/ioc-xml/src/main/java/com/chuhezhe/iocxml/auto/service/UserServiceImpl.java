package com.chuhezhe.iocxml.auto.service;

import com.chuhezhe.iocxml.auto.dao.UserDao;
import com.chuhezhe.iocxml.auto.dao.UserDaoImpl;

/**
 * ClassName: UserServiceImpl
 * Package: com.chuhezhe.iocxml.auto.service
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/17 21:26
 * @Version 1.0
 */
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUserService() {
        System.out.println("UserService execute....");

//        UserDao userDao = new UserDaoImpl();
        // 调用Dao中的方法进行实现
        userDao.add();
    }
}
