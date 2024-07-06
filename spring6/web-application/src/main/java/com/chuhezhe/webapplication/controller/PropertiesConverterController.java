package com.chuhezhe.webapplication.controller;

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

    // 通过 @GettingMap 注解的 consumes 属性指定了方法接收的媒体类型为 text/properties，
    // 如果方法能够成功调用，并且返回Properties对象，则说明我们自定义的HTTP消息转换器是可行的。
    @GetMapping(value = "/test1", consumes = "text/properties")
    public Properties test1(@RequestBody Properties properties) {
        return properties;
    }
}
