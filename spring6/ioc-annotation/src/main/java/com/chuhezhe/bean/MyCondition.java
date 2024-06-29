package com.chuhezhe.bean;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * ClassName: MyCondition
 * Package: com.chuhezhe.bean
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/29 11:03
 * @Version 1.0
 */
public class MyCondition implements Condition {
    /**
     * 指定组件注册的条件，即满足条件的组件才会被纳入到IOC容器中
     * @param context the condition context
     * @param metadata the metadata of the {@link org.springframework.core.type.AnnotationMetadata class}
     * or {@link org.springframework.core.type.MethodMetadata method} being checked
     * @return
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String osName = context.getEnvironment().getProperty("os.name");

        return "Windows".equals(osName); // windows 环境下才会被纳入到IOC容器中
    }
}
