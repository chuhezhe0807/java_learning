package com.chuhezhe.validator.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * ClassName: ValidationConfig
 * Package: com.chuhezhe.validator.annotation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 23:23
 * @Version 1.0
 */
@Configuration
@ComponentScan("com.chuhezhe.validator.annotation")
public class ValidationConfig {
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
