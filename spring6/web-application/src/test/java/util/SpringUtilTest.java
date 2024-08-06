package util;

import org.junit.jupiter.api.Test;
import org.springframework.util.ClassUtils;

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
}
