package com.chuhezhe.validator.customized;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

/**
 * ClassName: CanNotBlank
 * Package: com.chuhezhe.validator.customized
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/5 0:18
 * @Version 1.0
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {CanNotBlankValidation.class})
public @interface CanNotBlank {
    // 默认的错误信息
    String message() default "不能包含空格";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    public @interface List {
        CanNotBlank[] value();
    }
}
