package thread.threadpoolexcecutor.rejectpolicy;

/**
 * ClassName: RejectPolicyTask
 * Package: thread.threadpoolexcecutor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/16 21:50
 * @Version 1.0
 */
public class RejectPolicyTask implements Runnable {
    private final int index;

    public RejectPolicyTask(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : " + index);
    }
}
