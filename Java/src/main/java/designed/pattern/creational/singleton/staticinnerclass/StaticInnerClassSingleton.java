package designed.pattern.creational.singleton.staticinnerclass;

import java.io.Serializable;

/**
 * 静态内部类实现单例模式
 * 优点
 *      外部类加载时并不需要立即加载内部类，内部类不被加载则不去初始化INSTANCE，故而不占内存。
 *      即当SingleTon第一次被加载时，并不需要去加载SingleTonHoler，只有当getInstance()方法第一次被调用时，才会去初始化INSTANCE,
 *      第一次调用getInstance()方法会导致虚拟机加载SingleTonHoler类，这种方法不仅能确保线程安全，也能保证单例的唯一性，
 *      同时也延迟了单例的实例化。
 *
 * 缺点
 *      由于是静态内部类的形式去创建单例的，故外部无法传递参数进去
 *
 *
 * ClassName: StaticInnerClassSingleton
 * Package: designed.pattern.creational.singleton.staticinnerclass
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 21:12
 * @Version 1.0
 */
public class StaticInnerClassSingleton implements Serializable {
    public static final long serialVersionUID = 102345L;
    private static class InnerClass {
        private static final StaticInnerClassSingleton singleton = new StaticInnerClassSingleton();
    }

    private StaticInnerClassSingleton() {

    }

    public static StaticInnerClassSingleton getInstance() {
        return InnerClass.singleton;
    }

    // 反序列化时，底层会调用readResolve方法 防止反序列化破坏单例模式
    private Object readResolve() {
        return InnerClass.singleton;
    }
}
