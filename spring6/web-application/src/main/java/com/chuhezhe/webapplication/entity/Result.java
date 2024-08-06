package com.chuhezhe.webapplication.entity;

/**
 * ClassName: Result
 * Package: com.chuhezhe.webapplication.entity
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/31 23:34
 * @Version 1.0
 */
public class Result<T> {
    private Integer code; // 状态码
    private String message; // 返回信息
    private T data; // 数据

    // 封装返回数据
    public static<T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = new Result<>();

        if (body != null) {
            result.setData(body);
        }

        // 状态码
        result.setCode(resultCodeEnum.getCode());

        // 返回信息
        result.setMessage(resultCodeEnum.getMessage());

        return result;
    }

    // 私有化构造器
    private Result() {}

    // 成功
    public static<T> Result<T> ok() {
        return build(null, ResultCodeEnum.SUCCESS);
    }

    public static<T> Result<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    // 失败
    public static<T> Result<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }

    public static<T> Result<T> fail(T data) {
        return build(data, ResultCodeEnum.FAIL);
    }

    public Result<T> message(String msg){
        this.setMessage(msg);

        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);

        return this;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
