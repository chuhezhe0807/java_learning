package io.bio.stream.inputoutputstream;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

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

    // 读取、复制文本文件，推荐使用字符流防止出现编码问题
    @Test
    public void test03() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\helloWorld.txt";
        String destFilePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\helloWorld_字符_copy.txt";

        // 字符读取
//        try(
//                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))
//        ) {
//            int len;
//            char[] buff = new char[1024];
//            while((len = bufferedReader.read(buff)) != -1) {
//                System.out.println(new String(buff, 0, len));
//            }
//        }
//        catch(IOException e) {
//            e.printStackTrace();
//        }

        // 字符复制
        try (
                BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(destFilePath))
        ) {
            int len;
            char[] chars = new char[1024];

            while((len = bufferedReader.read(chars)) != -1) {
                bufferedWriter.write(chars, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 复制图片
    @Test
    public void test04() {
        String srcFilePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\input.jpg";
        String destFilePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\input_字符流复制.jpg";

        // 字节流
//        try(
//                FileInputStream fileInputStream = new FileInputStream(srcFilePath);
//                FileOutputStream fileOutputStream = new FileOutputStream(destFilePath)
//        ) {
//            int len;
//            byte[] bytes = new byte[8];
//
//            while((len = fileInputStream.read(bytes)) != -1) {
//                fileOutputStream.write(bytes, 0, len);
//            }
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }

        // 包装流
        try(
                BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(Paths.get(srcFilePath)));
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(Paths.get(destFilePath)))
        ) {
            int len;
            byte[] bytes = new byte[1024];

            while((len = bufferedInputStream.read(bytes)) != -1) {
                bufferedOutputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
