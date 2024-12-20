package thread.threadpoolexcecutor;

import java.util.concurrent.*;

/**
 * ClassName: ThreadPoolExecutorTest
 * Package: thread.threadpoolexcecutor
 * Description:
 *      workQueue一般分为直接提交队列、有界任务队列、无界任务队列和有限任务队列
 *          直接提交队列：SynchronousQueue 提交的任务不会被保存，总是会马上执行。如果执行任务的线程数小于最大线程数，则尝试创建新的线程，
 *              否则根据handler执行拒绝策略
 *          有界任务队列：ArrayBlockQueue 若有新的任务需要执行时，线程池会创建新的线程，直到创建的线程数量达到核心线程数，则会将新的任务
 *              加入到等待队列中。若等待队列已满，继续创建线程，直到线程数量达到最大线程数量，如果大于最大线程数量则执行handler拒绝策略
 *          无界任务队列：LinkedBlockingQueue 线程池的任务队列可以无限制的添加新的任务，而创建的最大线程数量就是【核心线程数】
 *              当线程池中的线程数量达到核心线程数后，就不会再增加了，若后续有新的任务加入，则直接进入队列等待。
 *          优先任务队列：PriorityBlockingQueue实现 PriorityBlockingQueue其实是一个特殊的无界队列，其中无论添加了多个任务，
 *              线程池创建的线程数也不会超过核心线程的数量，只不过 PriorityBlockingQueue 可以按照自定义的优先级顺序先后执行。
 *
 *      拒绝策略：
 *          AbortPolicy：直接抛出异常，阻止系统正常工作
 *          CallerRunsPolicy：如果线程池的线程数量达到上限，该策略会把任务队列中的任务放在调用者线程当中运行
 *          DiscardOldestPolicy：丢弃任务队列中最老的一个任务，也就是任务队列中最先被添加进去的
 *          DiscardPolicy策略：该策略会默默丢弃无法处理的任务，不予任何处理
 *
 * @Author Chuhezhe
 * @Create 2023/11/5 15:42
 * @Version 1.0
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ExecutorService executor = new ThreadPoolExecutor(
                3,      // 线程池中的核心线程数，他的数量决定了添加的任务是开辟新的线程去执行，还是放到workQueue中去
                5,                  // 线程池中的最大线程数量，根据使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量
                1L,                 // 当线程池中的线程数量超过核心线程数时，多余的线程会在多长时间内被销毁
                TimeUnit.SECONDS,   // keepAliveTime的单位
                new ArrayBlockingQueue<>(3),    // 任务队列，被添加到线程池中，但尚未被执行的任务
                Executors.defaultThreadFactory(),       // 线程工厂，一般使用默认即可
                new ThreadPoolExecutor.AbortPolicy()    // 拒绝策略，AbortPolicy 为直接抛出异常
        );

//        executor.execute(() -> System.out.println("running...."));

//        executor.submit(); // 与 executor.execute 的区别是，可以传入 Callable 接口实现类，有返回值

        for (int i = 0; i < 9; i++) {
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " ==> 办理业务");
            });
        }

        executor.shutdown();
    }
}
