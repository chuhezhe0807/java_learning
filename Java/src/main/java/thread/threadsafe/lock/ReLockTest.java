package thread.threadsafe.lock;

/**
 * 可重入锁
 *      一个线程中的多个流程可以获取同一把锁，持有这把同步锁可以再次进入 自己可以获取自己的内部锁
 *      ReentrantLock 和 synchronized 都是可重入锁
 *
 * ClassName: ReLockTest
 * Package: thread.threadsafe.relock
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/29 21:56
 * @Version 1.0
 */
public class ReLockTest {
    private static int i;
    private static final Object obj = new Object();

    public static void main(String[] args) {
        new Thread(ReLockTest::relock).start();
    }

    public static void relock() {
        synchronized (obj) {
            if(i == 3) {
                return;
            }
            else {
                System.out.println(++i + " 次调用");

                relock();
            }
        }
    }
}
