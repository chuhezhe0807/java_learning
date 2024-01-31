package thread.threadsafe.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: InterruptedLock
 * Package: thread.threadsafe.lock
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/29 23:28
 * @Version 1.0
 */
public class InterruptedLock {
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("线程1获取了锁");
                TimeUnit.SECONDS.sleep(5);
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                if(lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                lock.lockInterruptibly(); // 如果线程t2没有获取到这把锁，在等待的过程中调用了 t2.interrupt 方法，就会进入 catch (InterruptedException e)
                System.out.println("线程2获取了锁");
            } catch (InterruptedException e) {
                System.out.println("线程t2异常");
                e.printStackTrace();
            } finally {
                if(lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1); // 确保t1线程获取到了锁，确保线程t1先拿到锁
        t2.start();
        TimeUnit.SECONDS.sleep(1); // 确保线程t2正在等待获取锁
        t2.interrupt(); // 中断线程t2
    }
}
