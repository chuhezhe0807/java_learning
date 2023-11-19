package thread.countdownlatch;

import java.util.concurrent.CountDownLatch;

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
    private static boolean FLAG = false;

    public Task(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 就位");

        try {
            // 所有线程在此等待
            countDownLatch.await();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 抢夺标记方法
        onlyOne();
    }

    private static synchronized void onlyOne() {
        if(!FLAG) {
            System.out.println(Thread.currentThread().getName() + " 抢到了");
            FLAG = true;
        }
    }
}
