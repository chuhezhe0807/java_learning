package thread.threadsafe.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ClassName: FairLock
 * Package: thread.threadsafe.lock
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/30 23:36
 * @Version 1.0
 */
public class FairLock {
    private static final ReentrantLock fairLock = new ReentrantLock(true);

    public static class T extends Thread {
        public T(String name) {
            super(name);
        }

        @Override
        public void run() {
            for(int i = 0; i < 5; i++) {
                try {
                    fairLock.lock();

                    System.out.println(this.getName() + " 获取到锁!");
                }
                finally {
                    if(fairLock.isHeldByCurrentThread()) {
                        fairLock.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T t1 = new T("t1");
        T t2 = new T("t2");
        T t3 = new T("t3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}
