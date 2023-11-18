package thread.threadpoolexcecutor.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin框架的本质是一个用于并行执行任务的框架， 能够把一个大任务分割成若干个小任务，最终汇总每个小任务结果后得到大任务的计算结果。
 * 在Java中，ForkJoin框架与ThreadPool共存，并不是要替换ThreadPool
 *
 * ForkJoin框架是从jdk1.7中引入的新特性,它同ThreadPoolExecutor一样，也实现了Executor和ExecutorService接口。
 * 它使用了一个无限队列来保存需要执行的任务，而线程的数量则是通过构造函数传入，如果没有向构造函数中传入指定的线程数量，
 * 那么当前计算机可用的CPU数量会被设置为线程数量作为默认值。
 *
 * ForkJoinTask类中主要包括两个方法fork()和join()，分别实现任务的分拆与合并。
 *
 * ForkJoinTask有3个子类:
 *  RecursiveAction：无返回值的任务。
 *  RecursiveTask：有返回值的任务。
 *  CountedCompleter：完成任务后将触发其他任务。
 *
 * ClassName: Task
 * Package: thread.threadpoolexcecutor.forkjoin
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/18 20:25
 * @Version 1.0
 */
public class Task extends RecursiveTask<Long> {
    // 起始值
    private final long start;
    // 结束值
    private final long end;
    // 临界值
    private final static long CRITICAL_VALUE = 10L;

    public Task(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // 当开头与结尾之差小于临界值时
        if((end - start) < CRITICAL_VALUE) {
            // 记录计算结果
            long sum = 0L;
            // 累加从开头到结尾的值
            for(long i = start; i <= end; i++) {
                sum += i;
            }

            return sum;
        }
        else {
            // 取中间值
            long middle = (start + end) / 2;
            // 计算从开头累加到中间的值
            Task task1 = new Task(start, middle);
            // 向线程池中添加此任务
            task1.fork();
            // 计算从中间累加到结尾的值
            Task task2 = new Task(middle + 1, end);
            // 向线程池中添加此任务
            task2.fork();

            // 合并结果
            return task1.join() + task2.join();
        }
    }
}
