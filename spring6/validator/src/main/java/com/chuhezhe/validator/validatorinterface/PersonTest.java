package com.chuhezhe.validator.validatorinterface;

import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

/**
 * ClassName: PersonTest
 * Package: com.chuhezhe.validator
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 23:14
 * @Version 1.0
 */
public class PersonTest {
    public static void main(String[] args) {
        // 创建Person对象
        Person person = new Person();
        person.setAge(22);
        person.setName("xiaozhang");

        // 创建Person对应的dataBinder
        DataBinder dataBinder = new DataBinder(person);

        // 设置校验器
        dataBinder.setValidator(new PersonValidator());

        // 调用方法执行校验
        dataBinder.validate();

        // 输出校验结果
        BindingResult result = dataBinder.getBindingResult();
        System.out.println(result.getAllErrors());
    }
}
