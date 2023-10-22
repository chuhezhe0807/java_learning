package com.chuhezhe.resource.dao;

import org.springframework.stereotype.Repository;

/**
 * ClassName: UserRedisDaoImpl
 * Package: com.chuhezhe.autowired.dao
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/18 23:04
 * @Version 1.0
 */
@Repository("myUserRedisDao")
public class UserRedisDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("redis dao....");
    }
}
