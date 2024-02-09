package io.bio.stream.readerwriter;

import java.io.*;

/**
 * BufferedReader、BufferedWriter 是处理字符的，不要去操作二进制文件[声音, 视频, doc, pdf]等，容易造成文件损坏
 *
 * ClassName: BufferedCopy
 * Package: io.bio.stream.readerwriter
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 12:59
 * @Version 1.0
 */
public class BufferedRWCopy {
    public static void main(String[] args) {
        String srcFilePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\a.txt";
        String destFilePath = "D:\\WorkSpace_BackEnd\\TestFiles\\iofiles\\a_copy01.txt";
//        char[] buf = new char[1024];
//        int len;
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(srcFilePath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(destFilePath))) {
            // char[] 读取
//            while((len = br.read(buf)) != -1) {
//                bw.write(buf, 0, len);
//            }

            // 按行读取 BufferedReader.readLine 只读取一行的内容，写入时需要使用 BufferedWriter.newLine 加上换行符
            while((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
