package com.chuhezhe.configuration;

import com.chuhezhe.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: HelloWorldConfiguration
 * Package: com.chuhezhe.configuration
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/29 16:47
 * @Version 1.0
 */
@Configuration
public class HelloWorldConfiguration {
    @Bean
    public User helloWorldUser() {
        return new User();
    }
}
