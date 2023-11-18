package thread.threadpoolexcecutor.monitor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池监控
 *
 * ClassName: MonitorThreadPool
 * Package: thread.threadpoolexcecutor.monitor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/18 21:33
 * @Version 1.0
 */
public class MonitorThreadPool extends ThreadPoolExecutor {
    public MonitorThreadPool(int corePoolSize,
                             int maximumPoolSize,
                             long keepAliveTime,
                             TimeUnit unit,
                             BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        monitor();
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        monitor();
    }

    @Override
    protected void terminated() { // 线程池关闭时
        monitor();
    }

    // 监控线程池情况
    public void monitor() {
        System.out.print("正在工作的线程数：" + getActiveCount() + "\t");
        System.out.print("当前存在的线程数：" + getPoolSize() + "\t");
        System.out.print("历史最大的线程数：" + getLargestPoolSize() + "\t");
        System.out.print("已提交的任务数：" + getTaskCount() + "\t");
        System.out.print("已完成的任务数：" + getCompletedTaskCount() + "\t");
        System.out.println("队列中的任务数：" + getQueue().size());
    }
}
