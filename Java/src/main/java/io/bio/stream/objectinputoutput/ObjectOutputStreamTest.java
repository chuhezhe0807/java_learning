package io.bio.stream.objectinputoutput;

import java.io.*;

/**
 * ClassName: ObjectOutputStreamTest
 * Package: io.bio.stream.objectinputoutput
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 17:22
 * @Version 1.0
 */
public class ObjectOutputStreamTest {
    public static void main(String[] args) {
        String path = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\objoutputstream.dat";

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            // 基本数据类型会自动装箱
            oos.writeInt(100);
            oos.writeDouble(11.1);
            oos.writeBoolean(false);
            oos.writeUTF("string....");
            oos.writeChar('a');

            // 保存一个对象
            oos.writeObject(new Dog("xiaogou", 1));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Dog implements Serializable {
    private final String name;
    private final int age;
    private static final long serialVersionUID = -68112311009710L; // 序列化的版本号，可以提高兼容性
    private transient Integer integer = 123;
    public static String str = "12311";

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", integer=" + integer +
                '}';
    }
}
