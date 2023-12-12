package io.nio.channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ClassName: Demo1
 * Package: io.nio.channel
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/10 20:18
 * @Version 1.0
 */
public class WriteFileDemo {
    public static void main(String[] args) throws Exception {

        try (RandomAccessFile file = new RandomAccessFile("chuhezhe.txt", "rw")) {
            // 随机访问流
            FileChannel fileChannel;
            // 得到FileChannel
            fileChannel = file.getChannel();
            // 创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // fileChannel读取数据到buffer中
            int len = 0;
            while((len = fileChannel.read(buffer)) != -1) {
                // 在当前的java程序中把buffer中的数据展示出来
                // 把写的模式转换成读的模式
                buffer.flip();
                // 读取buffer中的数据
                while(buffer.hasRemaining()) {
                    // 获得buffer中的数据
                    byte b = buffer.get();
                    System.out.print((char) b);
                }

                // buffer清除
                buffer.clear();
            }
        }
    }
}
