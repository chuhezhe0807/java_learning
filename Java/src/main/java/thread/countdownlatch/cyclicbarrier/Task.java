package thread.countdownlatch.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: Task
 * Package: thread.countdownlatch.cyclicbarrier
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/19 12:11
 * @Version 1.0
 */
public class Task implements Runnable{
    // 循环屏障
    private final CyclicBarrier cyclicBarrier;

    public Task(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            // 模拟匹配时长
            TimeUnit.SECONDS.sleep((long) (Math.random() * 1000));
            System.out.println(Thread.currentThread().getName() + " 匹配成功");
            // 所有线程再次集合，一起往下执行
            cyclicBarrier.await();

            // 模拟准备时长
//            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + " 准备好了");
            // 所有线程再次集合，一起往下执行
            cyclicBarrier.await();

            // 模拟加载时长
//            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + " 加载完成");
            // 所有线程再次集合，一起往下执行
            cyclicBarrier.await();
        }
        catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
