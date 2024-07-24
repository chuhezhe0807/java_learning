package com.chuhezhe.webapplication.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 * ClassName: LoginSuccessHandler
 * Package: com.chuhezhe.webapplication.handler
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/20 22:22
 * @Version 1.0
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write("登陆成功！");
    }
}
