package designed.pattern.creational.singleton.lazy;

import java.io.Serializable;

/**
 * ClassName: LazySingleton
 * Package: designed.pattern.creational.singleton
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 19:37
 * @Version 1.0
 */
public class LazySingleton implements Serializable {
    public static final long serialVersionUID = 112334L;
    public static LazySingleton lazySingleton = null;

    private LazySingleton() {

    }

    public static synchronized LazySingleton getInstance() {
        if(null == lazySingleton) {
            lazySingleton = new LazySingleton();
        }

        return lazySingleton;
    }

    // 反序列化时，底层会调用readResolve方法 防止反序列化破坏单例模式
    private Object readResolve() {
        return lazySingleton;
    }
}
