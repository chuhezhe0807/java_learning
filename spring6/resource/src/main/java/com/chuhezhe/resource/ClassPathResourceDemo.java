package com.chuhezhe.resource;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName: ClassPathResourceDemo
 * Package: com.chuhezhe.resource
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 17:47
 * @Version 1.0
 */
// 访问类路径下的资源
public class ClassPathResourceDemo {
    public static void main(String[] args) {
        loadClassPathResource("chuhezhe.txt");
    }

    public static void loadClassPathResource(String path) {
        ClassPathResource resource = new ClassPathResource(path);

        System.out.println(resource.getFilename());
        System.out.println(resource.getDescription());

        // 获取文件内容
        try {
            InputStream in = resource.getInputStream();

            byte[] b = new byte[1024];
            while (in.read(b) != -1) {
                System.out.println(new String(b));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
