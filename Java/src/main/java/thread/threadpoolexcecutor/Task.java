package thread.threadpoolexcecutor;

/**
 * ClassName: Task
 * Package: thread.threadpoolexcecutor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/13 23:53
 * @Version 1.0
 */
public class Task implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
