package com.chuhezhe.webapplication.config;

import com.chuhezhe.webapplication.converter.PropertiesHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * ClassName: WebConfigurer
 * Package: com.chuhezhe.webapplication.config
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/6 16:43
 * @Version 1.0
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 将 PropertiesHttpMessageConverter 添加到消息转换器集合中，并且指定添加到第一个位置，
        // 防止被前面的 MappingJackson2HttpMessageConverter 提前处理 JSON 了。
        converters.add(0, new PropertiesHttpMessageConverter());
    }
}
