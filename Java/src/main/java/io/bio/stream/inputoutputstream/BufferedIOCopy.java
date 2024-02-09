package io.bio.stream.inputoutputstream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * ClassName: BufferedIOCopy
 * Package: io.bio.stream.inputoutputstream
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 14:47
 * @Version 1.0
 */
public class BufferedIOCopy {
    public static void main(String[] args) {
        String srcFilePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\input.jpg";
        String destFilePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\input_copy.jpg";
        byte[] bbuf = new byte[1024];
        int len;

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFilePath));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath))) {
            while((len = bis.read(bbuf)) != -1) {
                bos.write(bbuf, 0, len);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
