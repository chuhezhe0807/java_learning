package com.chuhezhe.webapplication.resolver;

import com.chuhezhe.webapplication.annotation.PropertiesRequestParameterResolver;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * ClassName: PropertiesHandlerMethodArgumentResolver
 * Package: com.chuhezhe.webapplication.resolver
 * Description:
 *
 *  HandlerMethodArgumentResolver 为方法参数解析器，用于解析由@RequestMapping注解（或其他派生的注解）所标注的方法的参数
 *  这里通过实现 HandlerMethodArgumentResolver 的方式来将HTTP请求体的内容自定解析为Properties对象
 *
 * @Author Chuhezhe
 * @Create 2024/7/6 22:12
 * @Version 1.0
 */
public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    // 指定支持解析的参数类型，这里只支持 Properties
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Properties.class.equals(parameter.getParameterType()) &&
           parameter.hasParameterAnnotation(PropertiesRequestParameterResolver.class);
    }

    // 实现解析逻辑
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        ServletWebRequest servletWebRequest = (ServletWebRequest) webRequest;
        HttpServletRequest request = servletWebRequest.getRequest();
        String contentType = request.getHeader("content-type");

        MediaType mediaType = MediaType.parseMediaType(contentType);
        // 获取编码
        Charset charset = mediaType.getCharset() == null ? StandardCharsets.UTF_8 : mediaType.getCharset();
        // 获取输入流
        ServletInputStream inputStream = request.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, charset);

        // 输入流转换为 Properties
        // 由于load(InputStream)默认总是以ASCII编码读取字节流，所以会导致读到乱码。我们需要用另一个重载方法load(Reader)读取
        Properties properties = new Properties();
        properties.load(inputStreamReader);

        return properties;
    }
}
