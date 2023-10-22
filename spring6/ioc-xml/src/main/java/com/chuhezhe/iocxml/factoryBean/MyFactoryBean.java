package com.chuhezhe.iocxml.factoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * ClassName: MyFactoryBean
 * Package: com.chuhezhe.iocxml.factoryBean
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/17 21:14
 * @Version 1.0
 */
public class MyFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<User> getObjectType() {
        return User.class;
    }
}
