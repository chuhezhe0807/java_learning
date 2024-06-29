package com.chuhezhe.lifeCycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * ClassName: WebConfig
 * Package: com.chuhezhe.lifeCycle
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/29 14:45
 * @Version 1.0
 */
@Configuration
public class WebConfig {
    @Lazy
    @Bean
//    @Bean(initMethod = "init", destroyMethod = "destroy") 出去此方式指定初始化和销毁方法外，还可以使用@PostConstruct 和 @PreDestroy
    public User myUser() {
        return new User();
    }

    /**
     * Bean 后置通知处理器
     */
    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
