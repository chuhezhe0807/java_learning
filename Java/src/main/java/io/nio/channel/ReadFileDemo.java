package io.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: ReadFileDemo
 * Package: io.nio.channel
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/10 20:47
 * @Version 1.0
 */
public class ReadFileDemo {
    public static void main(String[] args) throws Exception {
        // 创建随机访问流
        try(RandomAccessFile file = new RandomAccessFile("2.txt", "rw")) {
            // 获得fileChannel
            FileChannel fileChannel = file.getChannel();
            // 创建buffer对象
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 数据
            String data = "hello file channel.";
            // 存入buffer
            buffer.put(data.getBytes(StandardCharsets.UTF_8));
            // 翻转buffer
            buffer.flip();
            // fileChannel写数据到文件
            fileChannel.write(buffer);
        }
    }
}
