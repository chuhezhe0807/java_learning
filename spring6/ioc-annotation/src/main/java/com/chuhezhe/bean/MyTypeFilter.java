package com.chuhezhe.bean;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * ClassName: MyTypeFilter
 * Package: com.chuhezhe.bean
 * Description: 自定义扫描策略
 *
 * @Author Chuhezhe
 * @Create 2024/6/28 21:42
 * @Version 1.0
 */
public class MyTypeFilter implements TypeFilter {

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前正在扫描的类的注解信息
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前正在扫描的类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前正在扫描的类的路径等信息
        Resource resource = metadataReader.getResource();

        String className = classMetadata.getClassName();

        return className.endsWith("er"); // 制定了当被扫描的类名以 "er" 结尾的时候，匹配成功
    }
}
