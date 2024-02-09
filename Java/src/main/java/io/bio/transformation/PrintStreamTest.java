package io.bio.transformation;

import org.junit.jupiter.api.Test;

import java.io.*;

/**
 * ClassName: PrintStream
 * Package: io.bio.transformation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/9 11:43
 * @Version 1.0
 */
public class PrintStreamTest {
    // 打印流
    @Test
    public void test01() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\a.txt";

        // 当目标文件的编码与代码写入时的编码不一致时，需要使用到转换流
        try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath, true), "gbk");
            PrintWriter printWriter = new PrintWriter(osw)) {
//        try(PrintWriter printWriter = new PrintWriter(System.out)) { // 传入System.out 就输出到屏幕上
            printWriter.println("\n你好，小张");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
