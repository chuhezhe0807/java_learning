package io.bio.transformation;

import java.io.*;

/**
 * 演示字符流在不同编码下(代码读取时用的UTF8，文件编码确实GBK)出现的转换乱码问题
 *      字节流可以指定读文件时使用的字符集
 *      InputStreamReader(inputStream, Charset)
 *
 * ClassName: demo
 * Package: io.bio.transformation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 20:44
 * @Version 1.0
 */
public class demo {
    public static void main(String[] args) {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\a.txt";

        try(BufferedReader bis = new BufferedReader(new FileReader(filePath))) {
            System.out.println(bis.readLine()); // 文件是gbk编码的，读取的代码按照utf8读取的，所以会出现乱码的情况
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
