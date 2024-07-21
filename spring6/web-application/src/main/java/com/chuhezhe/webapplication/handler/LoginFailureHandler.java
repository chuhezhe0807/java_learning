package com.chuhezhe.webapplication.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

/**
 * ClassName: LoginFailureHandler
 * Package: com.chuhezhe.webapplication.handler
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/20 22:22
 * @Version 1.0
 */
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoginFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("exception: {}", exception.getMessage());

        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(exception.getMessage());
    }
}
