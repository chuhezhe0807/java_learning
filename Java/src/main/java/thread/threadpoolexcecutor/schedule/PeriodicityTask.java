package thread.threadpoolexcecutor.schedule;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: PeriodicityTask
 * Package: thread.threadpoolexcecutor.schedule
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/17 22:36
 * @Version 1.0
 */
public class PeriodicityTask implements Runnable{
    @Override
    public void run() {
        System.out.println(LocalTime.now());

        try {
            // 使当前线程休眠一秒钟，模拟任务执行时长
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
