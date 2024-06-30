package com.chuhezhe.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName: HelloWorldAutoConfiguration
 * Package: com.chuhezhe.configuration
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/30 12:14
 * @Version 1.0
 */
@Configuration
@EnableHelloWorld
@ConditionalOnProperty(name = "helloWorld", havingValue = "true")
public class HelloWorldAutoConfiguration {
}
