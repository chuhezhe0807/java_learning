package com.chuhezhe.Initializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;

/**
 * ClassName: AfterHelloApplicationContextInitializer
 * Package: com.chuhezhe.Initializer
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/30 17:34
 * @Version 1.0
 */
public class AfterHelloApplicationContextInitializer implements ApplicationContextInitializer, Ordered {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("AfterHelloApplicationContextInitializer");
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
