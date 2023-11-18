package thread.threadpoolexcecutor.shutdown;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.*;

/**
 * ClassName: ShutDownTest
 * Package: thread.threadpoolexcecutor.shutdown
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/16 22:30
 * @Version 1.0
 */
public class ShutDownTest {
    /**
     * 调用线程池的 shutdown 方法后
     *  1、不再接受新的任务
     *  2、继续执行完任务队列中的任务
     */
    @Test
    public void test01() {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,
                1,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1),
                new ThreadPoolExecutor.AbortPolicy()
        );

        try {
            threadPool.execute(new ShutdownTask(1));
            threadPool.execute(new ShutdownTask(2));
        }
        catch (RejectedExecutionException e) {
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown(); // 继续执行完任务队列中的任务

            threadPool.execute(new ShutdownTask(3)); // 抛出 RejectedExecutionException 异常
        }
    }

    /**
     * shutdownNow
     *
     * 关闭线程池，不再接收新任务
     * 尝试停止正在执行的任务，返回任务队列中的任务 List<Runnable>
     */
    @Test
    public void test02() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        threadPool.execute(new ShutdownTask(1));
        threadPool.execute(new ShutdownTask(2));
        threadPool.execute(new ShutdownTask(3));

        List<Runnable> tasks = threadPool.shutdownNow();

        for(Runnable task : tasks) {
            task.run();
        }
    }

    @Test
    public void test03() {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        threadPool.execute(new ShutdownNowTask(1));

        List<Runnable> tasks = threadPool.shutdownNow(); // 尝试停止正在执行的任务，并将任务队列中的任务返回

        for(Runnable task : tasks) {
            task.run();
        }
    }
}
