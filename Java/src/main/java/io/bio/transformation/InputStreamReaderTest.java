package io.bio.transformation;

import java.io.*;

/**
 * ClassName: InputStreamReaderTest
 * Package: io.bio.transformation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 22:43
 * @Version 1.0
 */
public class InputStreamReaderTest {
    public static void main(String[] args) {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\a.txt";

        try(InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath), "gbk");
            BufferedReader br = new BufferedReader(isr)) {
            System.out.println(br.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
