package com.chuhezhe.resource;

import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;

/**
 * ClassName: UrlResourceDemo
 * Package: com.chuhezhe.resource
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 17:35
 * @Version 1.0
 */
// 演示UrlResource访问网络资源
public class UrlResourceDemo {
    public static void main(String[] args) {
        // http前缀
//        loadUrlResource("http://www.baidu.com");

        // file前缀
        loadUrlResource("file:chuhezhe.txt");
    }

    // 访问前缀http、file
    public static void loadUrlResource(String path) {
        // 创建Resource实现类的对象UrlResource
        try {
            UrlResource url = new UrlResource(path);

            // 获取资源信息
            System.out.println(url.getFilename());
            System.out.println(url.getURI());
            System.out.println(url.getDescription());

            System.out.println(url.getInputStream().read());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
