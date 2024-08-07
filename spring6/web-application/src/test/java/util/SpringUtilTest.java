package util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.util.ClassUtils;
import org.springframework.util.FileSystemUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * ClassName: SpringUtilTest
 * Package: util
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/8/6 23:13
 * @Version 1.0
 */
public class SpringUtilTest {

    class B {

    }

    static class C {

    }

    // ClassUtils.isPresent(String className, @Nullable ClassLoader classLoader) 判断当前 classLoader 是否包含目标类型
    @Test
    public void test01() {
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        System.out.println(ClassUtils.isPresent("int", classLoader)); // true
        System.out.println(ClassUtils.isPresent("intt", classLoader)); // false
    }

    // resolvePrimitiveClassName(@Nullable String name) 通过给定类名获取原始类
    @Test
    public void test02() {
        System.out.println(ClassUtils.resolvePrimitiveClassName("int")); // int
        System.out.println(ClassUtils.resolvePrimitiveClassName("java.lang.Integer")); // null
        System.out.println(ClassUtils.resolvePrimitiveClassName("Void")); // null
    }

    // isPrimitiveWrapper(Class<?> clazz) 判断给定类是否为包装类
    @Test
    public void test03() {
        System.out.println(ClassUtils.isPrimitiveWrapper(Integer.class)); // true
        System.out.println(ClassUtils.isPrimitiveWrapper(int.class)); // false
        System.out.println(ClassUtils.isPrimitiveWrapper(Void.class)); // true
        System.out.println(ClassUtils.isPrimitiveWrapper(String.class)); // false
    }

    // resolvePrimitiveIfNecessary(Class<?> clazz) 如果给定类时原始类，则返回对应包装类，否则直接返回给定类
    @Test
    public void test04() {
        System.out.println(ClassUtils.resolvePrimitiveIfNecessary(int.class)); // class java.lang.Integer
        System.out.println(ClassUtils.resolvePrimitiveIfNecessary(Void.class)); // class java.lang.Void
    }

    // isAssignable(Class<?> lhsType, Class<?> rhsType) Return true if rhsType is assignable to lhsType.
    // 包装类型可以赋值给相应的原始类型，自动拆装箱机制
    @Test
    public void test05() {
        System.out.println(ClassUtils.isAssignable(Integer.class, int.class)); // true
        System.out.println(ClassUtils.isAssignable(int.class, Integer.class)); // true
        System.out.println(ClassUtils.isAssignable(RuntimeException.class, Exception.class)); // false
        System.out.println(ClassUtils.isAssignable(Exception.class, RuntimeException.class)); // true
    }

    // Class<?>[] getAllInterfaces(Object instance)  返回给定实例对象所实现的接口类型的数组
    @Test
    public void test06() {
        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        Class<?>[] allInterfaces = ClassUtils.getAllInterfaces(processor);
        Arrays.stream(allInterfaces).forEach(System.out::println);
    }

    //  isInnerClass(Class<?> clazz) 判断给定类是否是内部类(非静态)
    @Test
    public void test07() {
        System.out.println(ClassUtils.isInnerClass(B.class)); // true
        System.out.println(ClassUtils.isInnerClass(C.class)); // false
    }

    // hasConstructor(Class<?> clazz, Class<?>... paramTypes)
    @Test
    public void test08() {
        System.out.println(ClassUtils.hasConstructor(String.class, String.class)); // true
        System.out.println(ClassUtils.hasConstructor(String.class, Object.class)); // false
    }

    // hasMethod(Class<?> clazz, Method method) 给定类是否有指定的方法
    @Test
    public void test09() throws NoSuchMethodException {
        Method method = ClassUtils.class.getDeclaredMethod("hasMethod", Class.class, Method.class);
        System.out.println(ClassUtils.hasMethod(ClassUtils.class, method));
    }

    // getMethodIfAvailable(Class<?> clazz, String methodName, @Nullable Class<?>... paramTypes) 返回给定类的指定方法，如果不存在则返回null
    @Test
    public void test10() {
        System.out.println(ClassUtils.getMethodIfAvailable(ClassUtils.class, "hello")); // null
        System.out.println(ClassUtils.getMethodIfAvailable(ClassUtils.class, "hasMethod", Class.class, Method.class)); // Method
    }

    // getStaticMethod(Class<?> clazz, String methodName, Class<?>... args) 获取给定类的静态方法，如果没有该方法或者该方法不是静态的则返回null
    @Test
    public void test11() {
        Method method = ClassUtils.getStaticMethod(ClassUtils.class, "getDefaultClassLoader");
        System.out.println(method != null); // true
        System.out.println(method.getReturnType()); // class java.lang.ClassLoader
    }

    // boolean deleteRecursively(@Nullable File root) 递归地删除指定文件或者目录，删除成功返回true，失败返回false，不会抛出异常
    @Test
    public void test12() {
        File file = new File("a");
        System.out.println(FileSystemUtils.deleteRecursively(file));
    }

    // copyRecursively(File src, File dest) 递归地赋值src文件到dest，目标路径不存在则自动创建
    @Test
    public void test13() throws IOException {
        File srcFile = new File("aa");
        File destFile = new File("bb");
        FileSystemUtils.copyRecursively(srcFile, destFile);
    }
}
