package thread.threadpoolexcecutor.completionService;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * ClassName: TaskTest
 * Package: thread.threadpoolexcecutor.completionService
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/18 21:19
 * @Version 1.0
 */
public class TaskTest {
    @Test
    public void test01() {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        // 创建 ExecutorCompletionService 对象
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(threadPool);

        // 提交多个任务
        for(int i = 5; i >= 1; i--) {
            completionService.submit(new Task(i));
        }

        // 获取多个任务执行的结果
        try {
            for(int i = 1; i <= 5; i++) {
                Future<Integer> future = completionService.take();
                // 输出执行结果
                System.out.println("返回顺序: " + future.get());
            }
        }
        catch(InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown();
        }
    }
}
