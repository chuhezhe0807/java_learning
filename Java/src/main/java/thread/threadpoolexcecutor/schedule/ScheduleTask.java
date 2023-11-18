package thread.threadpoolexcecutor.schedule;

import java.time.LocalTime;

/**
 * ClassName: ScheduleTask
 * Package: thread.threadpoolexcecutor.schedule
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/17 22:02
 * @Version 1.0
 */
public class ScheduleTask implements Runnable{
    @Override
    public void run() {
        System.out.println(LocalTime.now());
    }
}
