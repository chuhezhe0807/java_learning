package io.bio.stream.inputoutputstream;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: FileOutputStream
 * Package: io.bio.stream.inputoutputstream
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/3 22:34
 * @Version 1.0
 */
public class FileOutputStreamTest {
    // 写入文件
    @Test
    public void test01() {
        String filePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\a.txt";

        // new FileOutputStream(String name, boolean append) 如果append为true，则写入内容会被追加到文件末尾
        try(FileOutputStream fos = new FileOutputStream(filePath, true)) {
            // 写入一个字节 'H'
//            fos.write('H');
            // 写入"Hello, world!"
            fos.write("Hello, world!".getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 拷贝图片文件
    @Test
    public void test02() {
        String inputPath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\input.jpg";
        String outputPath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\output.jpg";

        try(FileInputStream fis = new FileInputStream(inputPath);
            FileOutputStream fos = new FileOutputStream(outputPath)) {
            byte[] buf = new byte[1024];
            int len;

            while((len = fis.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
