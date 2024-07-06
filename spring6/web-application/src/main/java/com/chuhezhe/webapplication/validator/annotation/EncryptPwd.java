package com.chuhezhe.webapplication.validator.annotation;

import com.chuhezhe.webapplication.validator.EncryptIdValidator;
import jakarta.validation.Constraint;

import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * ClassName: EncryptId
 * Package: com.chuhezhe.webapplication.validator.annotation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/5 23:37
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EncryptIdValidator.class)
public @interface EncryptPwd {
    // 默认错误消息
    String message() default "加密格式错误";

    // 分组
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};
}
