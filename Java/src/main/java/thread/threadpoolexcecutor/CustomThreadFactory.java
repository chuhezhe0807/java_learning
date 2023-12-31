package thread.threadpoolexcecutor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: CustomThreadFactory
 * Package: thread.threadpoolexcecutor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/13 23:50
 * @Version 1.0
 */
public class CustomThreadFactory implements ThreadFactory {
    // 计数器
    private final AtomicInteger i = new AtomicInteger(1);

    @Override
    public Thread newThread(Runnable r) {
        // 创建线程，并指定任务
        Thread thread = new Thread(r);
        // 设置线程名称
        thread.setName("线程 " + i.getAndIncrement() + " 号");

        // 返回线程
        return thread;
    }
}
