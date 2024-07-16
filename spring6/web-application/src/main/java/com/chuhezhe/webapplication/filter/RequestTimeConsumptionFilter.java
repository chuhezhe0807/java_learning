package com.chuhezhe.webapplication.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;

/**
 * ClassName: TimeFilter
 * Package: com.chuhezhe.webapplication.filter
 * Description: 检测请求耗时的过滤器
 *
 * @Author Chuhezhe
 * @Create 2024/7/14 11:07
 * @Version 1.0
 */
//@Component
//@WebFilter(urlPatterns = "/*") // 可以通过 @WebFilter 配合 @Component 实现添加自定义过滤器，不过为保证类尽可能地保持最少知道原则，推荐在 WebConfig 中通过 FilterRegistrationBean 来注册过滤器
public class RequestTimeConsumptionFilter implements Filter {

    public static final String INIT_PARAMETER_NAME = "__excludedUris__";
    private static final Logger logger = LoggerFactory.getLogger(RequestTimeConsumptionFilter.class);
    private String[] excludedUris = null;

    @Override
    public void init(FilterConfig filterConfig) {
        logger.info("过滤器初始化");
        String excludedUris = filterConfig.getInitParameter(INIT_PARAMETER_NAME);

        if(StringUtils.hasLength(excludedUris)) {
            this.excludedUris = excludedUris.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(excludedUris != null && Arrays.stream(excludedUris).anyMatch(uri -> uri.equals(((HttpServletRequest) request).getRequestURI()))) {
            return;
        }

        logger.info("开始执行过滤器");
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        logger.info("【过滤器】请求耗时: {}", System.currentTimeMillis() - start);
        logger.info("结束执行过滤器");
    }

    @Override
    public void destroy() {
        logger.info("过滤器销毁");
    }
}
