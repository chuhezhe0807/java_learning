package thread.threadpoolexcecutor;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * ClassName: CustomThreadFactoryTest
 * Package: thread.threadpoolexcecutor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/13 23:55
 * @Version 1.0
 */
public class CustomThreadFactoryTest {
    @Test
    public void test1() {
        // 创建任务
        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();

        // 创建线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                10,
                25,
                10L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                new CustomThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        // 提交任务
        threadPool.execute(task1);
        threadPool.execute(task2);
        threadPool.execute(task3);

        // 关闭线程池
        threadPool.shutdown();
    }

    /**
     * Future<?> submit(Runnable task);
     */
    @Test
    public void test02() {
        Task task = new Task();
        // 单个任务的线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 提交任务
        Future<?> future = threadPool.submit(task);

        try {
            // 获取任务执行的结果
            Object res = future.get();
            // 输出任务执行结果
            System.out.println(res);
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        finally {
            // 关闭线程池
            threadPool.shutdown();
        }
    }

    /**
     * <T> Future<T> submit(Runnable task, T result); 将 T result 作为参数传递给线程池，任务执行完毕后使用
     */
    @Test
    public void test03() {
        Task task = new Task();
        // 单个任务的线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 提交任务
        Future<String> future = threadPool.submit(task, "任务完成!");

        try {
            // 获取任务执行的结果
            String res = future.get();
            // 输出任务执行结果
            System.out.println(res);
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        finally {
            // 关闭线程池
            threadPool.shutdown();
        }
    }

    /**
     * <T> Future<T> submit(Callable<T> task); Callable task.
     */
    @Test
    public void test04() {
        ResultTask resultTask = new ResultTask();
        // 单个任务的线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 提交任务
        Future<Integer> future = threadPool.submit(resultTask);

        try {
            // 获取任务执行的结果
//            Integer res = future.get(); // 阻塞式获取任务执行结果
//            Integer res = future.get(1, TimeUnit.SECONDS); // 阻塞式获取任务执行结果，超出设置的时间抛出超时异常
            Integer res = future.get(5, TimeUnit.SECONDS);
            // 输出任务执行结果
            System.out.println(res);
        }
        catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }
        finally {
            // 关闭线程池
            threadPool.shutdown();
        }
    }

    /**
     * Future.cancel 方法
     */
    @Test
    public void test05() {
        ResultTask resultTask1 = new ResultTask();
        ResultTask resultTask2 = new ResultTask();
        // 单个任务的线程池
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 提交任务
        Future<Integer> future1 = threadPool.submit(resultTask1);
        Future<Integer> future2 = threadPool.submit(resultTask2);

        // 线程池中的任务可分为三个阶段 未执行的任务（任务队列中）、正在执行的任务、已完成的任务
        // 取消未执行的任务
        boolean cancel = future2.cancel(false);// mayInterruptIfRunning 是否取消正在执行的任务 只对执行中的任务有效
        System.out.println("任务future2是否取消成功: " + cancel);
        System.out.println(future2.isCancelled());

        try {
            Integer res = future1.get();
            // 输出任务执行结果
            System.out.println(res);
        }
        catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        finally {
            // 关闭线程池
            threadPool.shutdown();
        }
    }

    /**
     * 取消正在执行的任务
     */
    @Test
    public void test06() {
        ResultTaskCounter task = new ResultTaskCounter();

        ExecutorService threadPool = Executors.newSingleThreadExecutor();

        Future<Integer> future = threadPool.submit(task);

        System.out.println("任务是否执行完成 " + future.isDone());

        // 当 mayInterruptIfRunning 为 true时任务不一定立马结束，任务若不响应中断线程指令时，就会继续执行完
        // 取消正在执行的任务task mayInterruptIfRunning 为 false 正在执行的task将会继续执行到完成
        // mayInterruptIfRunning 为 false 执行的结果：1、引发 CancellationException异常 2、任务继续执行完成
//        boolean cancel = future.cancel(false);
        boolean cancel = future.cancel(true);
        System.out.println("任务future是否取消成功: " + cancel);
        System.out.println(future.isCancelled());

        try {
            Integer integer = future.get();
            System.out.println(integer);
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        finally {
            threadPool.shutdown();
        }
    }
}
