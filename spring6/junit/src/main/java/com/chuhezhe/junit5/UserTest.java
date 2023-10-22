package com.chuhezhe.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * ClassName: UserTest
 * Package: com.chuhezhe.junit5
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/25 22:28
 * @Version 1.0
 */
@SpringJUnitConfig(locations = "classpath:bean.xml") // 指定Spring配置文件
public class UserTest {
    @Autowired
    private User user;

    @Test
    public void testMethod() {
        System.out.println(user);
        user.run();
    }
}
