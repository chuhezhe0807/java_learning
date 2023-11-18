package thread.threadpoolexcecutor.rejectpolicy;

import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: RejectPolicyTest
 * Package: thread.threadpoolexcecutor.rejectpolicy
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/16 21:54
 * @Version 1.0
 */
public class RejectPolicyTest {
    @Test
    public void test01() {
        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2),
//                new ThreadPoolExecutor.DiscardPolicy() // 直接丢弃任务
//                new ThreadPoolExecutor.CallerRunsPolicy() // 使用调用者线程直接执行被拒绝的任务
//                new ThreadPoolExecutor.AbortPolicy() // 默认的拒绝策略，抛出 RejectedExecutionException 异常
                new ThreadPoolExecutor.DiscardOldestPolicy() // 丢弃处于任务队列头部的任务，添加被拒绝的任务
        );

        try {
            // 提交任务
            threadPool.execute(new RejectPolicyTask(1));
            threadPool.execute(new RejectPolicyTask(2));
            threadPool.execute(new RejectPolicyTask(3));
            threadPool.execute(new RejectPolicyTask(4));
        }
        catch (RejectedExecutionException e) {
            e.printStackTrace();
        }
        finally {
            // 关闭线程池
            threadPool.shutdown();
        }
    }
}
