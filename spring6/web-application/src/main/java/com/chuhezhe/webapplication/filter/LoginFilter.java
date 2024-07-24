package com.chuhezhe.webapplication.filter;

import cn.hutool.json.JSONUtil;
import com.chuhezhe.webapplication.entity.UserDetail;
import com.chuhezhe.webapplication.handler.LoginFailureHandler;
import com.chuhezhe.webapplication.handler.LoginSuccessHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * ClassName: LoginFilter
 * Package: com.chuhezhe.webapplication.filter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/22 21:43
 * @Version 1.0
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    public LoginFilter(AuthenticationManager authenticationManager) {
        super();
        this.setAuthenticationManager(authenticationManager);
        this.setAuthenticationSuccessHandler(new LoginSuccessHandler());
        this.setAuthenticationFailureHandler(new LoginFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        UserDetail loginParameter;

        try {
            loginParameter = getLoginParameter(request);
        }
        catch (IOException e) {
            logger.info(e.getMessage());

            return null;
        }

        UsernamePasswordAuthenticationToken authRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginParameter.getUsername(), loginParameter.getPassword());

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 从请求体中获取登陆的 username, password 参数(UserDetail)
     *
     * @param request  servlet 对象
     * @return UserDetail
     */
    private UserDetail getLoginParameter(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader(); // 从请求体中获取数据
        StringBuilder sbf = new StringBuilder();
        String line;

        while((line = reader.readLine()) != null) {
            sbf.append(line);
        }

        return JSONUtil.parse(sbf.toString()).toBean(UserDetail.class);
    }
}
