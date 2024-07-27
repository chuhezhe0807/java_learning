package com.chuhezhe.webapplication.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * ClassName: RequestTimeConsumptionInterceptor
 * Package: com.chuhezhe.webapplication.interceptor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/14 14:52
 * @Version 1.0
 */
public class RequestTimeConsumptionInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestTimeConsumptionInterceptor.class);

    // 处理请求之前执行，是否将当前的请求拦截下来，如果返回false，请求将会终止
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        logger.info("请求处理之前");
        request.setAttribute("startTime", System.currentTimeMillis());

//        if(handler instanceof HandlerMethod) {
//            logger.info("handler bean name: {}", ((HandlerMethod) handler).getBean().getClass().getName());
//            logger.info("handler method name: {}", ((HandlerMethod) handler).getMethod().getName());
//        }

        return true;
    }

    // 在请求被处理之后调用 只有当被拦截的方法没有抛出异常，才会执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//        logger.info("请求处理之后");
//        long startTime = (long) request.getAttribute("startTime");
//        logger.info("【拦截器】请求耗时: {}", System.currentTimeMillis() - startTime);

        // modelAndView 指将被呈现在网页上的对象，可以通过修改这个对象，实现不同对象跳转到不同的网页
        if(modelAndView != null && request.getRequestURI().startsWith("/user/empty")) {
            Map map = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

            if(map.containsKey("id") && "999".equals(map.get("id"))) {
                modelAndView.setViewName("TempEmpty"); // 跳转到 /resources/templates/TempEmpty 页面
            }
        }
    }

    // 无论处理请求过程中是否抛出异常都会执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        logger.info("请求处理结束之后"); // 一般用于关闭流，资源连接等

        // Exception 如果被提前处理了（@ControllerAdvice 等处理），则为 null
        if(ex != null) {
//            long startTime = (long) request.getAttribute("startTime");
//            logger.info("【拦截器】请求耗时(请求过程中出现了异常): {}", System.currentTimeMillis() - startTime);
            logger.info("异常信息: {}", ex.getMessage());
        }
    }
}
