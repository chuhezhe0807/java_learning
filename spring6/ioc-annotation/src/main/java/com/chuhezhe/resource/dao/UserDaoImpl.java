package com.chuhezhe.resource.dao;

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
@Repository("myUserDao")
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("dao....");
    }
}
