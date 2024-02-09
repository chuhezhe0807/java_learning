package io.bio.stream.readerwriter;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

/**
 * ClassName: FileWriter
 * Package: io.bio.stream.readerwriter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/4 22:05
 * @Version 1.0
 */
public class FileWriterTest {
    @Test
    public void test01() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\a.txt";

        try(FileWriter fw = new FileWriter(filePath, true)) {
            // fw.write(char[] chBuf, 0, 12)
            fw.write("chBuf write here...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
