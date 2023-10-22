package com.chuhezhe.resource;

import org.springframework.core.io.FileSystemResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * ClassName: FileSystemResourceDemo
 * Package: com.chuhezhe.resource
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 18:19
 * @Version 1.0
 */
// 访问系统资源
public class FileSystemResourceDemo {
    public static void main(String[] args) {
        loadFileSystemResource("D:\\WorkSpace_BackEnd\\TestFiles\\chuhezhe.txt");
    }

    public static void loadFileSystemResource(String path) {
        FileSystemResource resource = new FileSystemResource(path);

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
