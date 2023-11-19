package thread.countdownlatch;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * java.util.concurrent.CountDownLatch
 * public CountDownLatch(int count) count指定倒计时初始值
 *
 * void await()                                 使当前线程等待，直到倒计时为0，或被中断
 * boolean await(long timeout, TimeUnit unit)   使当前线程等待，直到倒计时为0，或超时，亦或被中断
 * countDown() 计数递减
 * getCount() 获取计数
 *
 * ClassName: CountDownLatch
 * Package: thread.countdownlatch
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/18 22:29
 * @Version 1.0
 */
public class CountDownLatchTest {
    @Test
    public void test01() {
        // 创建 CountDownLatch 对象
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 创建多个线程并发执行
        for(int i = 0; i < 5; i++) {
            // 创建线程
            Thread thread = new Thread(new Task(countDownLatch), "线程 " + i + " 号");
            // 启动线程
            thread.start();
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 计数器减一
        countDownLatch.countDown();
    }
}
