package designed.pattern.creational.singleton.broker;

import designed.pattern.creational.singleton.doublechecked.DoubleChecked;
import designed.pattern.creational.singleton.enumsingleton.EnumSingleton;
import designed.pattern.creational.singleton.hungry.HungrySingleton;
import designed.pattern.creational.singleton.lazy.LazySingleton;
import designed.pattern.creational.singleton.staticinnerclass.StaticInnerClassSingleton;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * ClassName: TestSerializer
 * Package: designed.pattern.creational.singleton.broker
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 22:23
 * @Version 1.0
 */
public class TestSerializer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 懒汉式
//        LazySingleton instance = LazySingleton.getInstance();
        // DCL
        DoubleChecked instance = DoubleChecked.getInstance();
        // 饿汉式
//        HungrySingleton instance = HungrySingleton.getInstance();
        // 静态内部类
//        StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
        // 枚举
//        EnumSingleton instance = EnumSingleton.getInstance();

        // 可以通过序列化和反序列化的方式，创建对象
        Path singletonPath = Paths.get("singleton");
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(singletonPath));
        oos.writeObject(instance);

        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(singletonPath));
//        LazySingleton objInstance = (LazySingleton) ois.readObject();
        DoubleChecked objInstance = (DoubleChecked) ois.readObject();
//        HungrySingleton objInstance = (HungrySingleton) ois.readObject();
//        StaticInnerClassSingleton objInstance = (StaticInnerClassSingleton) ois.readObject();
//        EnumSingleton objInstance = (EnumSingleton) ois.readObject();

        System.out.println(instance);
        System.out.println(objInstance);
        System.out.println(instance == objInstance);
    }
}
