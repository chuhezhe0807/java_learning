package com.chuhezhe.dao.impl;

import com.chuhezhe.annotation.MyBean;
import com.chuhezhe.dao.UserDao;

/**
 * ClassName: UserDaoImpl
 * Package: dao.impl
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/20 23:47
 * @Version 1.0
 */
@MyBean
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("dao add......");
    }
}
