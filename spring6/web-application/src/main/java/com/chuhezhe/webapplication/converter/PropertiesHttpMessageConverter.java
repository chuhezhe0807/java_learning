package com.chuhezhe.webapplication.converter;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * ClassName: PropertiesHttpMessageConverter
 * Package: com.chuhezhe.webapplication.converter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/7/6 16:30
 * @Version 1.0
 */
public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {
    public PropertiesHttpMessageConverter() {
        // 添加响应的媒体的类型
        super(new MediaType("text", "properties"));
    }

    // 序列化过程，将响应序列化
    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        // 获取请求头
        HttpHeaders headers = outputMessage.getHeaders();
        // 获取 content-type
        MediaType contentType = headers.getContentType();
        // 获取编码格式
        Charset charset = null;

        if(contentType != null) {
            charset = contentType.getCharset();
        }

        charset = charset == null ? StandardCharsets.UTF_8 : charset;

        OutputStream body = outputMessage.getBody();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(body, charset);

        properties.store(outputStreamWriter, "Serialized by PropertiesHttpMessageConverter#writeInternal");
    }

    // 反序列化过程，将HTTP请求反序列化为参数的过程
    @Override
    protected Properties readInternal(Class<? extends Properties> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        // 获取请求头
        HttpHeaders headers = inputMessage.getHeaders();
        // 获取 content-type
        MediaType contentType = headers.getContentType();
        // 获取编码
        Charset charset = null;

        if(contentType != null) {
            charset = contentType.getCharset();
        }

        charset = charset == null ? StandardCharsets.UTF_8 : charset;

        // 获取请求体
        InputStream body = inputMessage.getBody();
        InputStreamReader inputStreamReader = new InputStreamReader(body, charset);

        Properties properties = new Properties();
        properties.load(inputStreamReader);

        return properties;
    }

    @Override
    public Properties read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null, inputMessage);
    }
}
