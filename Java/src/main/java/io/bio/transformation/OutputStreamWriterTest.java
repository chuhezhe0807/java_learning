package io.bio.transformation;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: OutputStreamWriterTest
 * Package: io.bio.transformation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 22:52
 * @Version 1.0
 */
public class OutputStreamWriterTest {
    public static void main(String[] args) {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\osw.txt";

        try(OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8);
            BufferedWriter bw = new BufferedWriter(osw)) {
            bw.write("hello, 小张一一小张一一");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
