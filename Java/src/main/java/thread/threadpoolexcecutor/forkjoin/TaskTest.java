package thread.threadpoolexcecutor.forkjoin;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * ClassName: TaskTest
 * Package: thread.threadpoolexcecutor.forkjoin
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/18 20:43
 * @Version 1.0
 */
public class TaskTest {
    @Test
    public void test01() {
        // 创建任务
        Task task = new Task(1L, 1000_0000L);
        // 创建ForkJoin线程池
        ForkJoinPool threadPool = new ForkJoinPool();
        // 提交任务
        ForkJoinTask<Long> future = threadPool.submit(task);

        try {
            System.out.println(LocalTime.now());
            System.out.println(future.get());
            System.out.println(LocalTime.now());
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown();
        }
    }

    @Test
    public void test02() {
        System.out.println(LocalTime.now());
        System.out.println(new Task(1L, 1000_0000L).compute());
        System.out.println(LocalTime.now());
    }
}
