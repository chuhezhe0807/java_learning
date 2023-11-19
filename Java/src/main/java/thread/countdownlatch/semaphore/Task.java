package thread.countdownlatch.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: Task
 * Package: thread.countdownlatch.semaphore
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/19 23:27
 * @Version 1.0
 */
public class Task implements Runnable{
    // 信号量
    private final Semaphore semaphore;

    public Task(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            // 获取许可证
            semaphore.acquire();
            // 模拟业务时长
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " 办好了");
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            // 释放许可证
            semaphore.release();
        }
    }
}
