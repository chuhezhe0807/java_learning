package io.bio.stream.stanardio;

/**
 * 标准输入输出流
 *
 * ClassName: InputAndOutput
 * Package: io.bio.stream.stanardio
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/8 19:13
 * @Version 1.0
 */
public class InputAndOutput {
    public static void main(String[] args) {
        // System 类的 System.in public static InputStream in = null;
        // System.in 编译类型 InputStream
        // System.in 运行类型 BufferedInputStream
        System.out.println(System.in.getClass()); // java.io.BufferedInputStream

        // System.out public static PrintStream out = null;
        // System.out 编译类型 PrintStream
        // System.out 运行类型 PrintStream
        System.out.println(System.out.getClass()); // java.io.PrintStream
    }
}
