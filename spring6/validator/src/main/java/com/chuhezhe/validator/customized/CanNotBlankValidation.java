package com.chuhezhe.validator.customized;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * ClassName: CanNotBlankValidation
 * Package: com.chuhezhe.validator.customized
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/5 0:19
 * @Version 1.0
 */
public class CanNotBlankValidation implements ConstraintValidator<CanNotBlank, String> {
    @Override
    public void initialize(CanNotBlank constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value != null && value.contains(" ")) {
            //获取默认提示信息
            String defaultConstraintMessageTemplate = context.getDefaultConstraintMessageTemplate();
            System.out.println("default message :" + defaultConstraintMessageTemplate);
            //禁用默认提示信息
            context.disableDefaultConstraintViolation();
            //设置提示语
            context.buildConstraintViolationWithTemplate("can not contains blank 新替换的").addConstraintViolation();
            return false;
        }

        return true;
    }
}
