package com.chuhezhe.resourceloader;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * ClassName: ResourceLoaderDemo
 * Package: com.chuhezhe.resourceloader
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 18:28
 * @Version 1.0
 */
public class ResourceLoaderDemo {
    @Test
    public void demo1() {
        ApplicationContext context = new ClassPathXmlApplicationContext();

        Resource res = context.getResource("chuhezhe.txt");
        System.out.println(res.getFilename());
        System.out.println(res instanceof ClassPathResource);
    }

    @Test
    public void demo2() {
        ApplicationContext context = new FileSystemXmlApplicationContext();

        Resource res = context.getResource("chuhezhe.txt");
        System.out.println(res.getFilename());
        System.out.println(res instanceof FileSystemResource);
    }
}
