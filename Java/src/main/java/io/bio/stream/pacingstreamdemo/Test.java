package io.bio.stream.pacingstreamdemo;

/**
 * ClassName: Test
 * Package: io.bio.stream.pacingstreamdemo
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 11:30
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        BufferedReader_ bufferedReader_ = new BufferedReader_(new FilesReader_());
        bufferedReader_.readFiles(10);

        // 读字符串
        BufferedReader_ bufferedReader_1 = new BufferedReader_(new StrReader_());
        bufferedReader_1.readStr(2);
    }
}
