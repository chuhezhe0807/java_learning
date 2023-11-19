package thread.countdownlatch.starter;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

/**
 * ClassName: TaskTest
 * Package: thread.countdownlatch
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/18 22:29
 * @Version 1.0
 */
public class TaskTest {
    @Test
    public void test01() {
        // 创建 CountDownLatch 对象
        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 创建多个线程并发执行
        for(int i = 1; i <= 3; i++) {
            // 创建线程
            Thread thread = new Thread(new Task(countDownLatch), "线程 " + i + " 号");
            // 启动线程
            thread.start();
        }

        try {
            // 等待计数器减为0
            countDownLatch.await();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("开始合并...");
    }
}
