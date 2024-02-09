package io.bio.stream.readerwriter;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

/**
 * ClassName: FileReaderTest
 * Package: io.bio.stream.readerwriter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/4 21:39
 * @Version 1.0
 */
public class FileReaderTest {
    @Test
    public void test01() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\helloWorld.txt";

        try(FileReader fr = new FileReader(filePath)) {
            char[] buf = new char[1024];
            int len;

            while((len = fr.read(buf)) != -1) {
                System.out.print(new String(buf, 0, len));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
