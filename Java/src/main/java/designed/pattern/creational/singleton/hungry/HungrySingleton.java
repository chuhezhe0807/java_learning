package designed.pattern.creational.singleton.hungry;

import java.io.Serializable;

/**
 * ClassName: HungrySingleton
 * Package: designed.pattern.creational.singleton.hungry
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 21:33
 * @Version 1.0
 */
public class HungrySingleton implements Serializable {
    public static final long serialVersionUID = 10234L;
    private static final HungrySingleton HUNGRY_SINGLETON = new HungrySingleton();

    private HungrySingleton() {};

    public static HungrySingleton getInstance() {
        return HUNGRY_SINGLETON;
    }

    // 反序列化时，底层会调用readResolve方法 防止反序列化破坏单例模式
    private Object readResolve() {
        return HUNGRY_SINGLETON;
    }
}
