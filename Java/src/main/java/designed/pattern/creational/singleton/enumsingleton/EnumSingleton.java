package designed.pattern.creational.singleton.enumsingleton;

import java.io.Serializable;

/**
 * ClassName: EnumSingleton
 * Package: designed.pattern.creational.singleton.enumsingleton
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 22:10
 * @Version 1.0
 */
public enum EnumSingleton {
    INSTANCE;
    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
