package com.chuhezhe.bean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ClassName: MyImportSelector
 * Package: com.chuhezhe.bean
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/6/29 11:18
 * @Version 1.0
 */
public class MyImportSelector implements ImportSelector {
    /**
     * 返回需要导入IOC容器的全类名数组
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "com.chuhezhe.bean.ClassToBeBatchImport01",
                "com.chuhezhe.bean.ClassToBeBatchImport02",
                "com.chuhezhe.bean.ClassToBeBatchImport03"
        };
    }
}
