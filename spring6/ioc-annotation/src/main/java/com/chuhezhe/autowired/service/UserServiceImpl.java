package com.chuhezhe.autowired.service;

import com.chuhezhe.autowired.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserServiceImpl
 * Package: com.chuhezhe.autowired.service
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/18 22:11
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    @Qualifier("userRedisDaoImpl")
    private UserDao userDao;

    @Override
    public void add() {
        System.out.println("service....");
        userDao.add();
    }
}
