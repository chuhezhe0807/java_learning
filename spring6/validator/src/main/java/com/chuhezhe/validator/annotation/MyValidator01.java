package com.chuhezhe.validator.annotation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * ClassName: MyValidator01
 * Package: com.chuhezhe.validator.annotation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 23:29
 * @Version 1.0
 */
@Service
public class MyValidator01 {
    private Validator validator;

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public boolean validatorByUserOne(User user) {
        Set<ConstraintViolation<User>> validate = validator.validate(user);

        return validate.isEmpty();
    }
}
