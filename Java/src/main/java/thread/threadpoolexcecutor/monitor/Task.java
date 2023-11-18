package thread.threadpoolexcecutor.monitor;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: Task
 * Package: thread.threadpoolexcecutor.monitor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/18 21:33
 * @Version 1.0
 */
public class Task implements Runnable{
    // 模拟任务执行执行时间
    private final int executionTime;

    public Task(int executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(executionTime);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
