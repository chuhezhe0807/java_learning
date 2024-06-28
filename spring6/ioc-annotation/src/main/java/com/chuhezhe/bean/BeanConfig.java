package com.chuhezhe.bean;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

/**
 * ClassName: BeanConfig
 * Package: com.chuhezhe.bean
 * Description:
 *
 * @ComponentScan(includeFilters = {}) 时需要注意加上 useDefaultFilters = false 来关闭Spring默认的扫描策略
 *
 * 组件作用域：
 *      单实例（默认）,在Spring IOC容器启动的时候会调用方法创建对象然后纳入到IOC容器中，以后每次获取都是直接从IOC容器中获取（map.get()）；
 *
 * @Author Chuhezhe
 * @Create 2024/6/26 23:36
 * @Version 1.0
 */
@Configuration
//@ComponentScan(
//        value = "com.chuhezhe.bean",
//        excludeFilters = {
//                @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Repository.class, Controller.class}),
//                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = UserExcludeByAssignableType.class)
//        }
//)
@ComponentScan(
        value = "com.chuhezhe.bean",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class) // 使用自定义规则
        }
)
public class BeanConfig {
    // @Lazy 在单例模式中，IOC容器创建的时候不会马上去调用方法创建对象并注册，只有当组件第一次被使用的时候才会调用方法创建对象并加入到容器中
    @Lazy
    @Bean
    public User user() {
        System.out.println("向容器中添加Bean");
        return new User("xiaozhang", 26);
    }
}
