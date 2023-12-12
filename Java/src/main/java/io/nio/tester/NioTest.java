package io.nio.tester;

/**
 * NIO线程模型
 * NIO包含三大组件
 * 1、Channel通道：每个通道对应一个buff缓冲区
 * 2、Buffer缓冲区：buffer底层是数组，类似于蓄水池，channel就是水管
 * 3、Selector选择器：selector对应一个或多个线程。channel会注册到selector上，由selector根据channel读写事件的发生交给莫格空闲线程来执行。
 * Buffer和Channel都是即可读也可写。
 *
 * ClassName: NioTest
 * Package: io.nio.tester
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/5 22:50
 * @Version 1.0
 */
public class NioTest {
}
