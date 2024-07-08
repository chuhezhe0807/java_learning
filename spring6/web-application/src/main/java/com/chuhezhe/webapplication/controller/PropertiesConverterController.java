package com.chuhezhe.webapplication.controller;

import com.chuhezhe.webapplication.annotation.PropertiesRequestParameterResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * ClassName: PropertiesConverterController
 * Package: com.chuhezhe.webapplication.controller
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/6 16:47
 * @Version 1.0
 */
@RestController()
@RequestMapping("/propertiesconverter")
public class PropertiesConverterController {

    // 通过 @GettingMap 注解的 consumes 属性指定了处理请求的提交内容类型（Content-type）, 此处指定处理的请求的 content-type 为 text/properties
    // produces 指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回
    // 如果方法能够成功调用，并且返回Properties对象，则说明我们自定义的HTTP消息转换器是可行的。
    @GetMapping(value = "/test1", consumes = "text/properties")
    public Properties test1(@RequestBody Properties properties) {
        return properties;
    }

    // 使用 @PropertiesRequestParameterResolver 注解标注，自定义参数解析器
    @GetMapping(value = "/test2", consumes = "text/properties")
    public Properties test2(@PropertiesRequestParameterResolver Properties properties) {
        return properties;
    }
}
