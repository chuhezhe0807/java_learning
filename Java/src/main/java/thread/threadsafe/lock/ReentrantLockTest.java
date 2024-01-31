package thread.threadsafe.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 手动的显式的上锁、解锁 jdk 实现的
 * synchronized 隐式的上锁、解锁 jvm 实现 底层是c++
 *
 * ClassName: ReentrantLockTest
 * Package: thread.threadsafe.lock
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/27 12:57
 * @Version 1.0
 */
public class ReentrantLockTest {
    private static Integer stock = 10_0000;
    private static ReentrantLock reentrantLock = new ReentrantLock();

    static class StockRunnable implements Runnable {
        @Override
        public /*synchronized*/ void run() {
            try {
                reentrantLock.lock();
                stock--;
            }
            finally {
                // 最好解锁之前加上判断
                // 判断锁是否是在当前线程加锁
                if(reentrantLock.isHeldByCurrentThread()) {
                    reentrantLock.unlock(); // ReentrantLock 更加灵活
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        StockRunnable task = new StockRunnable();

        try {
            for(int i = 0; i < 10_0000; i++) {
                threadPool.execute(task);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown();
            // 等待关闭
            threadPool.awaitTermination(1000, TimeUnit.SECONDS);
        }

        System.out.println("剩余库存" + stock);
    }
}
