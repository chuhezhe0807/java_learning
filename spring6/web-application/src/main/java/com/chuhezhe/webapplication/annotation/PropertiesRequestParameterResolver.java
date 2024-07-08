package com.chuhezhe.webapplication.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: PropertiesRequestParameterResolver
 * Package: com.chuhezhe.webapplication.annotation
 * Description: 标识被注解的请求参数需要使用
 * {@link com.chuhezhe.webapplication.resolver.PropertiesHandlerMethodArgumentResolver} 解析
 *
 * @Author Chuhezhe
 * @Create 2024/7/8 23:20
 * @Version 1.0
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertiesRequestParameterResolver {
}
