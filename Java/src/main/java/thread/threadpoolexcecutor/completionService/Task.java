package thread.threadpoolexcecutor.completionService;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * public interface CompletionService<V> 返回结果采用"执行优先"原则，先执行完先返回 V 为指定任务结果的类型
 *
 * ClassName: Task
 * Package: thread.threadpoolexcecutor.completionService
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/18 21:11
 * @Version 1.0
 */
public class Task implements Callable<Integer> {
    // 执行时间
    private final int executionTime;

    public Task(int executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public Integer call() throws Exception {
        // 输出执行时间
        System.out.println("执行顺序: " + executionTime);

        Thread.sleep(executionTime * 100L);

        return executionTime;
    }
}
