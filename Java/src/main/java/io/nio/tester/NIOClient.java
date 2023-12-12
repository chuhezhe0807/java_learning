package io.nio.tester;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * ClassName: NIOClient
 * Package: io.nio.tester
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/7 22:53
 * @Version 1.0
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        // 获得Channel通道
        SocketChannel channel = SocketChannel.open();
        // 设置成非阻塞
        channel.configureBlocking(false);
        // 获得Selector
        Selector selector = Selector.open();
        // 客户端连接上服务端
        channel.connect(new InetSocketAddress("127.0.0.1", 9090));
        // 将channel注册到selector上，并且监听连接事件
        channel.register(selector, SelectionKey.OP_CONNECT);

        // 轮询访问selector
        for(;;) {
            // 阻塞的，在客户端的角度这个方法只面向一个客户端的channel
            selector.select();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while(iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if(selectionKey.isConnectable()) {
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    // 如果是正在连接，则完成连接
                    if(socketChannel.isConnectionPending()) {
                        // finishConnect 这个方法执行才表示连接完成了
                        socketChannel.finishConnect();
                        // 设置成非阻塞的方式
                        socketChannel.configureBlocking(false);
                        // 给服务端发送消息
                        socketChannel.write(ByteBuffer.wrap("Hello server.".getBytes()));
                        // 获得服务端返回的数据
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    }
                }
                else if(selectionKey.isReadable()) {
                    // 读服务端返回的数据
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    // 创建缓冲区
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int len = socketChannel.read(byteBuffer);

                    while(len != -1) {
                        System.out.println("服务端返回的数据是: " + new String(byteBuffer.array(), 0, len));
                    }
                }

                // 防止重复处理事件
                iterator.remove();
            }
        }
    }
}
