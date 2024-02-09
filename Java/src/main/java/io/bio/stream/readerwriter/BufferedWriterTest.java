package io.bio.stream.readerwriter;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ClassName: BufferedWriterTest
 * Package: io.bio.stream.readerwriter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 12:09
 * @Version 1.0
 */
public class BufferedWriterTest {
    @Test
    public void test01() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\a.txt";

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            // 插入一个和系统相关的换行符
            bufferedWriter.newLine();
            bufferedWriter.write("bufferedWriter.write...");
            bufferedWriter.newLine();
            bufferedWriter.write("bufferedWriter.write new line here...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
