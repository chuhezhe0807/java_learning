package thread.threadsafe.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock 提供尝试获取锁的方法，并且可以设置等待时间
 *
 * ClassName: ReentrantTryLock
 * Package: thread.threadsafe.lock
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/29 22:45
 * @Version 1.0
 */
public class ReentrantTryLock {
    private static ReentrantLock lock1 = new ReentrantLock(false);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + ": " + this.getName() + "开始获取锁!");

            try {
                // tryLock 尝试获取锁，如果所锁占用，返回false，没有占用返回true，可以指定获取锁的等待时间
                if(lock1.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println(System.currentTimeMillis() + ": " + this.getName() + "获取到了锁！");
                    // 获取到锁后，休眠5秒
                    TimeUnit.SECONDS.sleep(5);
                }
                else {
                    System.out.println(System.currentTimeMillis() + ": " + this.getName() + "未能获取到锁!");
                }
            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                if(lock1.isHeldByCurrentThread()) {
                    lock1.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        T t1 = new T("t1");
        T t2 = new T("t2");

        t1.start();
        t2.start();
    }
}
