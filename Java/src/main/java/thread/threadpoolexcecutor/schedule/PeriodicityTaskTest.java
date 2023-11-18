package thread.threadpoolexcecutor.schedule;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: PeriodicityTaskTest
 * Package: thread.threadpoolexcecutor.schedule
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/17 22:38
 * @Version 1.0
 */
public class PeriodicityTaskTest {
    @Test
    public void test01() {
        PeriodicityTask periodicityTask = new PeriodicityTask();
        // 创建调度线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);

        System.out.println(LocalTime.now());

        // scheduleAtFixedRate 固定时间，任务开始后固定时间后下一个任务开始执行
        // scheduleWithFixedDelay 固定间隔时间，任务结束后的指定时间后下一个任务开始执行
//        ScheduledFuture<?> future = threadPool.scheduleAtFixedRate(periodicityTask, 1L, 1L, TimeUnit.SECONDS);
        ScheduledFuture<?> future = threadPool.scheduleWithFixedDelay(periodicityTask, 1L, 1L, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 取消任务
        future.cancel(false);

        threadPool.shutdown();
    }
}
