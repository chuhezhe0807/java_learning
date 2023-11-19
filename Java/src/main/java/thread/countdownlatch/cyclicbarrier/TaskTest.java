package thread.countdownlatch.cyclicbarrier;

import org.junit.jupiter.api.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName: TaskTest
 * Package: thread.countdownlatch.cyclicbarrier
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/19 12:19
 * @Version 1.0
 */
public class TaskTest {
    @Test
    public void test01() {
        // 创建循环屏障对象 CyclicBarrier
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> System.out.println(Thread.currentThread().getName()));

        // 创建多个线程并发执行
        for(int i = 1; i <= 5; i++) {
            Thread thread = new Thread(new Task(cyclicBarrier), "玩家 " + i + " 号");
            // 启动线程
            thread.start();
        }
    }

    @Test
    public void test02() {
        ExecutorService service = Executors.newCachedThreadPool();
        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.out.println("全部到达 " + Thread.currentThread().getName() + " 呼叫服务员开始点餐！");
                service.shutdown();
            }
        });
        for (int j = 0; j < 5; j++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "同学到达");
                        barrier.await();
                        System.out.println(Thread.currentThread().getName()+"同学点餐");
                    }
                    catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        service.shutdown();
    }
}
