package com.chuhezhe.configuration;

import com.chuhezhe.importselector.HelloWorldImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * ClassName: EnableHelloWorld
 * Package: com.chuhezhe.configuration
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/29 16:49
 * @Version 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class) // 引入指定的配置类
@Import(HelloWorldImportSelector.class) // 引入指定的 ImportSelector 类也可以实现上面引入指定类的效果
public @interface EnableHelloWorld {
}
