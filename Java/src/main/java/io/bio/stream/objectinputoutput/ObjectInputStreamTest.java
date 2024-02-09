package io.bio.stream.objectinputoutput;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * ClassName: ObjectInputStreamTest
 * Package: io.bio.stream.objectinputoutput
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 17:42
 * @Version 1.0
 */
public class ObjectInputStreamTest {
    public static void main(String[] args) {
        String path = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\objoutputstream.dat";

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            // 注意：读取的顺序要和写入的顺序一致
            System.out.println(ois.readInt());
            System.out.println(ois.readDouble());
            System.out.println(ois.readBoolean());
            System.out.println(ois.readUTF());
            System.out.println(ois.readChar());

            // 需要强转，注意类的位置能否引用到
            Dog dog1 = (Dog) ois.readObject();
            System.out.println(dog1);

            System.out.println(dog1.getName());
            System.out.println(dog1.str); // 静态的属性没有必要使用类的实例访问，直接使用类访问即可
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
