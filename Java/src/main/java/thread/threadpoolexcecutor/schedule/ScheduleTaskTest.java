package thread.threadpoolexcecutor.schedule;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ScheduleTaskTest
 * Package: thread.threadpoolexcecutor.schedule
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/17 22:03
 * @Version 1.0
 */
public class ScheduleTaskTest {
    @Test
    public void test01() {
        ScheduleTask task = new ScheduleTask();

        // 创建调度线程池
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(5);
        // 输出当前时间
        System.out.println("test: " + LocalTime.now());

        // 提交任务 延迟3秒执行，放到线程中后延时
        threadPool.schedule(task, 3, TimeUnit.SECONDS);
        // 关闭线程池
        threadPool.shutdown();
    }
}
