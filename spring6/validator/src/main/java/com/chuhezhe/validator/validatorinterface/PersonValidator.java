package com.chuhezhe.validator.validatorinterface;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * ClassName: PersonValidator
 * Package: com.chuhezhe.validator
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 23:05
 * @Version 1.0
 */
public class PersonValidator implements Validator {
    // 对哪一个类做校验
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    // 校验规则
    @Override
    public void validate(Object target, Errors errors) {
        // name 不能为空
        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty", "name is null.");

        // age 范围为 [0, 200]
        Person p = (Person) target;
        if(p.getAge() < 0) {
            errors.rejectValue("age", "age.value.error.smaller", "age < 0");
        } else if(p.getAge() > 200) {
            errors.rejectValue("age", "age.value.error.bigger", "age > 200");
        }
    }
}
