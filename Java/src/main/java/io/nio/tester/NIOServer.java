package io.nio.tester;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * ClassName: NIOServer
 * Package: io.nio.tester
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/6 23:02
 * @Version 1.0
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建ServerSocketChannel对象
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 设置为非阻塞的方式
        ssc.configureBlocking(false);
        // 设置服务程序端口
        ssc.socket().bind(new InetSocketAddress(9090));
        // 创建Selector多路复用器
        Selector selector = Selector.open();
        // 把ServerSocketChannel对象注册到Selector上，并且告知对客户端的连接这样的事件感兴趣的服务器
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        for(;;) {
            System.out.println("等待事件发生");
            // 阻塞 轮询监听所有注册到Selector上的channel
            int select = selector.select();
            System.out.println("某个事件发生了");

            // 获得所有发生时间的channel
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()) {
                // selectionKey ==> 对应着一个channel
                SelectionKey selectionKey = iterator.next();
                handle(selectionKey);
                // 防止重复处理
                iterator.remove();
            }
        }
    }

    private static void handle(SelectionKey selectionKey) throws IOException {
        // 判断channel上发生了什么事情
        if(selectionKey.isAcceptable()) {
            System.out.println("有客户端连接了");
            // 服务端处理客户端的连接，得到ServerSocketChannel，代表着服务器
            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
            // 服务端接收客户端的连接，得到socketChannel，建立起了服务端和客户端具体的连接通道（阻塞的）
            SocketChannel socketChannel = channel.accept();
            // 把socketChannel设置成非阻塞
            socketChannel.configureBlocking(false);
            // 把socketChannel注册到selector上，并且关心读事件的发生(即客户端向服务端写入的数据)
            socketChannel.register(selectionKey.selector(), SelectionKey.OP_READ);
        }
        else if(selectionKey.isReadable()) {
            System.out.println("有客户端向服务端写数据");
            // 获取服务端和客户端之间的SocketChannel
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            // 创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 通过SocketChannel读到buffer中，返回的是这一次读取的字节个数
            int len = socketChannel.read(buffer);

            if(len != -1) {
                System.out.println("读到客户端的数据：" + new String(buffer.array(), 0, len));
            }

            // 通过SocketChannel向客户端写入数据
            socketChannel.write(ByteBuffer.wrap("Hello NIO.".getBytes()));
        }
    }
}
