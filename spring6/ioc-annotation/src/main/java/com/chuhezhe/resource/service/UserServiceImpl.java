package com.chuhezhe.resource.service;

import com.chuhezhe.resource.dao.UserDao;
import jakarta.annotation.Resource;
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
@Service("myUserService")
public class UserServiceImpl implements UserService {

    @Resource(name = "myUserDao")
    private UserDao userDao;

    @Override
    public void add() {
        System.out.println("service....");
        userDao.add();
    }
}
