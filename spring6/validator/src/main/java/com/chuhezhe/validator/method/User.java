package com.chuhezhe.validator.method;

import com.chuhezhe.validator.customized.CanNotBlank;
import jakarta.validation.constraints.*;

/**
 * ClassName: User
 * Package: com.chuhezhe.validator.method
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/5 0:03
 * @Version 1.0
 */
public class User {
    @NotNull
    private String name;
    @Min(0)
    @Max(150)
    private int age;
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$", message = "手机号码格式错误")
    @NotBlank(message = "手机号码不能为空")
    private String phone;
    @CanNotBlank
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
