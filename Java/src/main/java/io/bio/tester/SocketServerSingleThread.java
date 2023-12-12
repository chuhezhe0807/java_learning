package io.bio.tester;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: SocketServerSingleThread
 * Package: io.nio.tester
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/4 23:38
 * @Version 1.0
 */
public class SocketServerSingleThread {
    public static void main(String[] args) throws IOException {
        // 创建服务端的Socket
        ServerSocket serverSocket = new ServerSocket(9090);

        for(;;) {
            System.out.println("等待客户端的连接");
            // 阻塞等待客户端的连接
            Socket socket = serverSocket.accept();
            System.out.println("已经有客户端连接了");
            // 开始处理客户端的读写请求
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            // 阻塞的等待客户端向io流通道中写数据
            int len = inputStream.read(bytes);
            System.out.println("收到客户端的数据" + new String(bytes, 0, len));
            // 服务端返回信息给客户端
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("success".getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }
}
