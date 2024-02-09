package io.bio.stream.readerwriter;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * ClassName: BufferedReaderTest
 * Package: io.bio.stream.readerwriter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 12:01
 * @Version 1.0
 */
public class BufferedReaderTest {
    @Test
    public void test01() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\helloWorld.txt";
        String line;

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while(null != (line = bufferedReader.readLine())) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
