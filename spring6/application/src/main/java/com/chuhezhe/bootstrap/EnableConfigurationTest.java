package com.chuhezhe.bootstrap;

import com.chuhezhe.configuration.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

/**
 * ClassName: EnableConfigurationTest
 * Package: com.chuhezhe.bootstrap
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/29 16:50
 * @Version 1.0
 */
//@EnableHelloWorld
@EnableAutoConfiguration // TODO EnableAutoConfiguration 未能实现，还需要再看看
public class EnableConfigurationTest {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(EnableConfigurationTest.class)
                .web(WebApplicationType.NONE)
                .run(args);

        String[] names = context.getBeanDefinitionNames();
        Arrays.stream(names).forEach(System.out::println); // 包含有 helloWorldUser，证明基于自定义注解驱动的 @EnableHelloWorld 成功了

        context.close();
    }
}
