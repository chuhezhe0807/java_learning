package com.chuhezhe.resourceloaderaware;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

/**
 * ClassName: TestDemo
 * Package: com.chuhezhe.resourceloaderaware
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/4 18:36
 * @Version 1.0
 */
public class TestBean implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;

    //实现ResourceLoaderAware接口必须实现的方法
    //如果把该Bean部署在Spring容器中，该方法将会有Spring容器负责调用。
    //SPring容器调用该方法时，Spring会将自身作为参数传给该方法。
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
