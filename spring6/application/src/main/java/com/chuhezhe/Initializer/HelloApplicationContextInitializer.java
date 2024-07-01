package com.chuhezhe.Initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * ClassName: HelloApplicationContextInitializer
 * Package: com.chuhezhe.Initializer
 * Description:
 *
 * SpringApplication 准备阶段：
 *      1、配置源
 *      2、推断应用类型
 *      3、加载应用上下文初始器
 *      4、加载应用事件监听器
 *      5、推断入口类
 *
 * SpringApplication 运行阶段：
 *      1、开启事件监听
 *      2、开启运行监听器
 *
 * @Author Chuhezhe
 * @Create 2024/6/30 17:32
 * @Version 1.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE) // 使用@Ordered注解标识初始化器的优先级，数值越高，优先级越高，出去注解方式外，也可以通过 实现 Ordered 接口重写 getOrder 方法实现
public class HelloApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("HelloApplicationContextInitializer.id " + applicationContext.getId());
    }
}
