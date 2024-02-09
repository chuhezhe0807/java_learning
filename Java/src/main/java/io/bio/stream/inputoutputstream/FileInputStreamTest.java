package io.bio.stream.inputoutputstream;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * ClassName: FileInputStream
 * Package: io.bio.stream.inputoutputstream
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/3 19:45
 * @Version 1.0
 */
public class FileInputStreamTest {
    // 创建FileInputStream输入流对象，用于读取文件
    // 使用 FileInputStream.read() 方法
    @Test
    public void test01() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\helloWorld.txt";

        try(FileInputStream fileInputStream = new FileInputStream(filePath)) {
            int readData;

            // fileInputStream.read() 从该输入流中读取一个字节的数据。返回-1表示读取完毕
            while((readData = fileInputStream.read()) != -1) {
                System.out.print((char) readData);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 使用 FileInputStream.read(byte[] bytes)
    @Test
    public void test02() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\helloWorld.txt";

        try(FileInputStream fileInputStream = new FileInputStream(filePath)) {
            byte[] readData = new byte[8]; // 一次读8个字节的数据
            int len;

            // fileInputStream.read(byte[] bytes) 从该输入流中读取最多readData.length字节的数据到字节数组。
            // 如果读取正常，返回实际读取的字节数 返回-1表示读取完毕
            while((len = fileInputStream.read(readData)) != -1) {
                System.out.print(new String(readData, 0, len));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
