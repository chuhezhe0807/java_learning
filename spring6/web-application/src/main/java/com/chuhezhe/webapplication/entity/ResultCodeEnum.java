package com.chuhezhe.webapplication.entity;

/**
 * ClassName: ResultCodeEnum
 * Package: com.chuhezhe.webapplication
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/31 23:36
 * @Version 1.0
 */
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(2012, "服务异常"),
    DATA_ERROR(204, "数据异常"),

    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限")
    ;

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
