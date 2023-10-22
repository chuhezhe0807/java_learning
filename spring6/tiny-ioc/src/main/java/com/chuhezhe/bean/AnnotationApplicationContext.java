package com.chuhezhe.bean;

import com.chuhezhe.annotation.MyBean;
import com.chuhezhe.annotation.MyDi;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * ClassName: AnnotationApplicationContext
 * Package: bean
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/21 22:26
 * @Version 1.0
 */
public class AnnotationApplicationContext implements ApplicationContext{
    private Map<Class, Object> beanFactory = new HashMap<>();
    private static String rootPath;

    // 创建有参构造，传递包路径，设置包扫描规则
    // 当前包及其子包，哪个类由@Bean注解，把这个类通过反射实例化。
    public AnnotationApplicationContext(String basePackage) {
//    public static void pathDemo1(String basePackage) {
        try {
            // basePackage eg: com.chuhezhe
            String packagePath = basePackage.replaceAll("\\.", "\\\\");

            // 获取包的绝对路径
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packagePath);

            while(urls.hasMoreElements()) {
                // 获取包所在的完整路径
                URL url = urls.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8);

                // 获取包名前面的部分
                rootPath = filePath.substring(0, filePath.length() - packagePath.length());

                // 包扫描过程
                loadBean(new File(filePath));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 包扫描过程，实例化
    private void loadBean(File file) throws Exception {
        // 1、判断当前是否是文件夹
        if(file.isDirectory()) {
            // 2、获取文件夹里面的所有内容
            File[] childrenFiles = file.listFiles(); // File.listFiles() 获取文件见里面的所有文件包括文件夹

            // 3、判断文件夹里面为空，直接返回
            if(childrenFiles == null || childrenFiles.length == 0) {
                return;
            }

            // 4、如果文件夹里面不为空，遍历文件夹所有内容
            for (File child : childrenFiles) {
                    // 4.1 遍历得到每一个File对象，继续判断，如果还是文件夹 递归
                if(child.isDirectory()) {
                    loadBean(child);
                } else {
                    // 4.2 遍历得到File对象不是文件夹，是文件，
                    // 4.3 得到路径+类名称部分-字符串截取
                    String pathWithClass = child.getAbsolutePath().substring(rootPath.length() - 1);

                    // 4.4 判断当前文件类型是否是.class
                    if(pathWithClass.contains(".class")) {
                        // 4。5 如果是.class类型，把路径\替换成. 把.class去掉
                        // com.chehezhe.service.UserServiceImpl
                        String allName = pathWithClass.replaceAll("\\\\", ".").replace(".class", "");

                        // 4.6 判断类上面是否有@Bean注解，如果有实例化
                        Class<?> clazz = Class.forName(allName);
                        if(!clazz.isInterface()) {
                            MyBean annotation = clazz.getAnnotation(MyBean.class);

                            if(annotation != null) {
                                // 4.7 把对象实例化之后放到map集合中beanFactory
                                Object instance = clazz.getDeclaredConstructor().newInstance();

                                if(clazz.getInterfaces().length > 0) {
                                    beanFactory.put(clazz.getInterfaces()[0], instance);
                                } else {
                                    beanFactory.put(clazz, instance);
                                }
                            }
                        }
                    }
                }
            }
        }

        // 属性注入
        loadBi();
    }

    // 属性注入
    private void loadBi() {
        // 实例化对象在beanFactory的map集合里面
        // 1、遍历beanFactory的map集合
        Set<Map.Entry<Class, Object>> entries = beanFactory.entrySet();
        for (Map.Entry<Class, Object> entry : entries) {
            // 2、获取map集合每个对象（value）
            Object obj = entry.getValue();

            // 3、遍历得到的每个对象属性数组，得到每个属性
            Class<?> clazz = obj.getClass();
            Field[] declaredFields = clazz.getDeclaredFields();

            // 4、判断属性上面是否有@Di注解
            for (Field field : declaredFields) {
                MyDi annotation = field.getAnnotation(MyDi.class);
                if(annotation != null) {
                    // 如果访问限制，设置访问权限
                    field.setAccessible(true);

                    // 5、如果有@Di注解，把对象进行设置（注入）
                    try {
                        field.set(obj, beanFactory.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }
}
