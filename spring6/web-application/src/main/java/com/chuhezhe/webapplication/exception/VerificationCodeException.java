package com.chuhezhe.webapplication.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * ClassName: VerificationCodeException
 * Package: com.chuhezhe.webapplication.exception
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/31 22:14
 * @Version 1.0
 */
public class VerificationCodeException extends AuthenticationException {

    public VerificationCodeException() {
        super("图形验证码验证失败!");
    }
}
