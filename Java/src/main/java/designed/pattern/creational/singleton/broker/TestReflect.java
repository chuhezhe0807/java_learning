package designed.pattern.creational.singleton.broker;

import designed.pattern.creational.singleton.lazy.LazySingleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * ClassName: TestReflect
 * Package: designed.pattern.creational.singleton.broker
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 23:38
 * @Version 1.0
 */
public class TestReflect {
    @SuppressWarnings("rawtypes")
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 创建字节码对象
        Class clz = LazySingleton.class;
        // 创建构造器对象
        Constructor constructor = clz.getDeclaredConstructor();
        constructor.setAccessible(true);
        // 初始化单例对象
        LazySingleton clzInstance = (LazySingleton) constructor.newInstance();

        LazySingleton instance = LazySingleton.getInstance();

        System.out.println(clzInstance);
        System.out.println(instance);
        System.out.println(clzInstance == instance);
    }
}
