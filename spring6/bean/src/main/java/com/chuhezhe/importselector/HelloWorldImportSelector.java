package com.chuhezhe.importselector;

import com.chuhezhe.configuration.HelloWorldConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ClassName: HelloWorldSelector
 * Package: com.chuhezhe.selector
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/30 12:08
 * @Version 1.0
 */
public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{HelloWorldConfiguration.class.getName()};
    }
}
