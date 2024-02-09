package io.bio.stream.pacingstreamdemo;

/**
 * ClassName: BufferedReader_
 * Package: io.bio.stream.pacingstreamdemo
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 11:24
 * @Version 1.0
 */
public class BufferedReader_ extends Reader_{
    private final Reader_ reader;

    public BufferedReader_(Reader_ reader) {
        this.reader = reader;
    }

    // 让方法更加灵活，多次读取文件
    public void readFiles(int num) {
        for(int i = 0; i < num; i++) {
            reader.readFiles();
        }
    }

    // 让方法更加灵活，多次读取字符串
    public void readStr(int num) {
        for(int i = 0; i < num; i++) {
            reader.readStr();
        }
    }
}
