package com.chuhezhe.validator.method;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * ClassName: ValidationConfig
 * Package: com.chuhezhe.validator.method
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/5 0:01
 * @Version 1.0
 */
@Configuration
@ComponentScan("com.chuhezhe.validator.method")
public class ValidationConfig {
    @Bean
    public MethodValidationPostProcessor validationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}
