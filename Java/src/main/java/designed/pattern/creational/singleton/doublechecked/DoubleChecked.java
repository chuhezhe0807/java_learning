package designed.pattern.creational.singleton.doublechecked;

import java.io.Serializable;

/**
 * ClassName: DoubleChecked
 * Package: designed.pattern.creational.singleton.doublechecked
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 20:45
 * @Version 1.0
 */
public class DoubleChecked implements Serializable {
    public static final long serialVersionUID = 112311L;
    private static volatile DoubleChecked doubleChecked = null; // DoubleChecked locing 中变量需要使用volatile修饰，保证CPU执行的有序性

    private DoubleChecked() {};

    public static DoubleChecked getInstance() {
        if(null == doubleChecked) {
            synchronized (DoubleChecked.class) {
                if(null == doubleChecked) {
                    doubleChecked = new DoubleChecked();
                }
            }
        }

        return doubleChecked;
    }

    // 反序列化时，底层会调用readResolve方法 防止反序列化破坏单例模式
    private Object readResolve() {
        return doubleChecked;
    }
}
