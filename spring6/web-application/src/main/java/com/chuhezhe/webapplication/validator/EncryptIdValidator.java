package com.chuhezhe.webapplication.validator;


import com.chuhezhe.webapplication.validator.annotation.EncryptPwd;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName: EncryptIdValidator
 * Package: com.chuhezhe.webapplication.validator
 * Description:
 *
 * 注意:
 *      自定义校验类实现的 ConstraintValidator接口 应该是 jakarta.validation 包下的
 *
 * @Author Chuhezhe
 * @Create 2024/7/5 23:32
 * @Version 1.0
 */
public class EncryptIdValidator implements ConstraintValidator<EncryptPwd, String> {
    private static final Pattern PATTERN = Pattern.compile("^[a~f\\d]{1,5}$"); // 由数字或者a-f的字母组成，1-5长度

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value != null) {
            Matcher matcher = PATTERN.matcher(value);

            return matcher.find();
        }

        return false;
    }
}
