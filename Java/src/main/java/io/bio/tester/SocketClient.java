package io.bio.tester;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: SocketClient
 * Package: io.nio.tester
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/4 23:48
 * @Version 1.0
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        // 1、连接服务器
        Socket socket = new Socket("localhost", 9090);
        // 2、发送数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello bio from client 1".getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        // 3、接受服务端返回的数据
        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = inputStream.read(bytes);
        System.out.println("接受到服务端返回的数据: " + new String(bytes, 0, len));
        // 4、关闭连接
        socket.close();
    }
}
