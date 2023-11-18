package thread.threadpoolexcecutor.invokeall;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * ClassName: InvokeAllTest
 * Package: thread.threadpoolexcecutor.invokeall
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/17 21:27
 * @Version 1.0
 */
public class InvokeAllTest {

    // InvokeAll 批量执行任务
    @Test
    public void test01() {
        Set<InvokeTask> tasks = new HashSet<>();

        // 填充任务列表
        for(int i = 1; i <= 10; i++) {
            tasks.add(new InvokeTask(i));
        }

        // 创建固定线程池数量的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {
            // 提交任务列表
//            List<Future<Integer>> futures = threadPool.invokeAll(tasks);
            // 规定时间内完成的任务返回给调用者，未完成的任务全部取消，取消后抛出任务取消异常
            List<Future<Integer>> futures = threadPool.invokeAll(tasks, 1L, TimeUnit.SECONDS);
            // 遍历执行结果列表
            for(Future<Integer> future:futures) {
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        Set<InvokeTask> tasks = new HashSet<>();

        // 填充任务列表
        for(int i = 1; i <= 10; i++) {
            tasks.add(new InvokeTask(i));
        }

        // 创建固定线程池数量的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {
            // invokeAny 返回任务列表中最先完成的任务执行结果，取消其余所有任务
            Integer result = threadPool.invokeAny(tasks);
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
