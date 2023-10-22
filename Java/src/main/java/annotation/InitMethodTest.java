package annotation;

import java.lang.reflect.Method;

/**
 * ClassName: AnnotationTest
 * Package: com.chuhezhe.annotation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/3 17:03
 * @Version 1.0
 */

// 元注解：修饰注解的注解
// @Target 指定注解针对的地方
// @Retention 指定注解的保留域
//      RetentionPolicy.SOURCE 源代码级别，由编译器处理，处理之后不再保留
//      RetentionPolicy.CLASS 注解信息保留到类对应的class文件中
//      RetentionPolicy.RUNTIME 由JVM读取，运行时使用。

public class InitMethodTest {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.chuhezhe.annotation.InitDemo");

        Method[] methods = clazz.getMethods();

        for (Method method : methods) {
            boolean isAnnotationPresent = method.isAnnotationPresent(InitMethod.class);

            if (isAnnotationPresent) {
                method.invoke(clazz.getConstructor().newInstance());
            }
        }
    }
}
