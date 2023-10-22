package com.chuhezhe.autowired.dao;

import org.springframework.stereotype.Repository;

/**
 * ClassName: UserDaoImpl
 * Package: com.chuhezhe.autowired.dao
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/18 22:11
 * @Version 1.0
 */
@Repository
public class UserDaoImpl implements UserDao{
    @Override
    public void add() {
        System.out.println("dao....");
    }
}
