package com.chuhezhe.validator.method;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

/**
 * ClassName: MyService
 * Package: com.chuhezhe.validator.method
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/5 0:07
 * @Version 1.0
 */
@Service
@Validated
public class MyService {
    public String testMethod(@NotNull @Valid User user) {
        return user.toString();
    }
}
