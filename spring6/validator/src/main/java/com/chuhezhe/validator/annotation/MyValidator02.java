package com.chuhezhe.validator.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.Validator;

/**
 * ClassName: MyValidator02
 * Package: com.chuhezhe.validator.annotation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 23:29
 * @Version 1.0
 */
@Service
public class MyValidator02 {
    private Validator validator;

    @Autowired
    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public boolean validatorByUserTwo(User user) {
        BindException bindException = new BindException(user, user.getName());
        validator.validate(user, bindException);

        System.out.println(bindException.getAllErrors());
        return bindException.hasErrors();
    }
}
