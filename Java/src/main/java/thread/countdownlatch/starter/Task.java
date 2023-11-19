package thread.countdownlatch.starter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: Task
 * Package: thread.countdownlatch
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/18 22:33
 * @Version 1.0
 */
public class Task implements Runnable{
    private final CountDownLatch countDownLatch;

    public Task(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            // 模拟下载时长
            TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 1000));
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + " 已下载完成");
        // 计数减一
        countDownLatch.countDown();
    }
}
