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
public class ShutdownNowTask implements Runnable {
    private final int index;

    public ShutdownNowTask(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        for(;;) {
            // 加入响应线程中断的逻辑
            // Thread.interrupted 调用之后返回中断状态，并将中断状态置为false
            // isInterrupted(成员方法)-->返回当前中断状态
            if(Thread.interrupted()) {
                return;
            }

            System.out.println(Thread.currentThread().getName() + " : " + index);
        }
    }
}
