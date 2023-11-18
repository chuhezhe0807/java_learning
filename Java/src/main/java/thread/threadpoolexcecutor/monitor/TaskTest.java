package thread.threadpoolexcecutor.monitor;

import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: TaskTest
 * Package: thread.threadpoolexcecutor.monitor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/18 21:44
 * @Version 1.0
 */
public class TaskTest {
    @Test
    public void test01() {
        // 创建一个带监控功能的线程池
        MonitorThreadPool monitorThreadPool = new MonitorThreadPool(
                1,
                3,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2));

        try {
            // 提交多个任务
            for(int i = 5; i >= 1; i--) {
                monitorThreadPool.execute(new Task(i));
                // 每隔500毫秒提交一个任务
                TimeUnit.MILLISECONDS.sleep(500L);
            }

            // 使主线程休眠6秒钟
            TimeUnit.SECONDS.sleep(6);
            // 关闭线程池之前获取一次情况
            monitorThreadPool.monitor();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            monitorThreadPool.shutdown();
        }
    }
}
