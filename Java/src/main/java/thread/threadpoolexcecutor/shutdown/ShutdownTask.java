package thread.threadpoolexcecutor.shutdown;

/**
 * ClassName: RejectPolicyTask
 * Package: thread.threadpoolexcecutor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/16 21:50
 * @Version 1.0
 */
public class ShutdownTask implements Runnable {
    private final int index;

    public ShutdownTask(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : " + index);
    }
}
