package com.chuhezhe.webapplication.config;

import com.chuhezhe.webapplication.converter.PropertiesHttpMessageConverter;
import com.chuhezhe.webapplication.filter.RequestTimeConsumption;
import com.chuhezhe.webapplication.resolver.PropertiesHandlerMethodArgumentResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * ClassName: WebConfigurer
 * Package: com.chuhezhe.webapplication.config
 * Description:
 *
 *     " @Autowired按byType自动注入，而@Resource默认按 byName自动注入
 *
 * @Author Chuhezhe
 * @Create 2024/7/6 16:43
 * @Version 1.0
 */
@Configuration
@EnableAsync // 开启异步支持
public class WebConfigurer implements WebMvcConfigurer {

    // 添加检测请求耗时的自定义过滤器，多个过滤器可以向Spring容器中添加多个 FilterRegistrationBean
    @Bean
    public FilterRegistrationBean<RequestTimeConsumption> timeFilter() {
        FilterRegistrationBean<RequestTimeConsumption> filterFilterRegistrationBean = new FilterRegistrationBean<>();

        Set<String> urlSet = new HashSet<>();
        urlSet.add("/*");

        filterFilterRegistrationBean.setFilter(new RequestTimeConsumption()); // 检测请求耗时的过滤器
        filterFilterRegistrationBean.setUrlPatterns(urlSet);
        filterFilterRegistrationBean.addInitParameter(RequestTimeConsumption.INIT_PARAMETER_NAME, "/favicon.ico,/excludeURI");
        filterFilterRegistrationBean.setName("RequestTimeConsumption");
        filterFilterRegistrationBean.setOrder(1); // 数字越小，执行越早

        return filterFilterRegistrationBean;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 将 PropertiesHttpMessageConverter 添加到消息转换器集合中，并且指定添加到第一个位置，
        // 防止被前面的 MappingJackson2HttpMessageConverter 提前处理 JSON 了。
        converters.add(0, new PropertiesHttpMessageConverter());
    }

    // 添加自定义的请求方法参数解析器
    // Properties 本质也是一个Map，而spring内置的 MapMethodProcessor 就是处理Map类型参数的，所以需要把 PropertiesHandlerMethodArgumentResolver 添加到第一个位置
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(0, new PropertiesHandlerMethodArgumentResolver());
    }

    // 处理跨域
    // 所有请求都支持跨域访问，且不限定域，但是支持GET请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET");
    }
}
