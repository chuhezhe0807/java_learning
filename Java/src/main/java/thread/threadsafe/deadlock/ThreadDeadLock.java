package thread.threadsafe.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: ThreadDeadLock
 * Package: thread.threadsafemore.deadlock
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/21 23:18
 * @Version 1.0
 */
public class ThreadDeadLock {
    public static void main(String[] args) {
        Dress dress = new Dress();
        Trousers trousers = new Trousers();

        // t1和t2共享 dress和trousers
        Thread t1 = new Thread(new MyRunnable1(dress, trousers));
        Thread t2 = new Thread(new MyRunnable2(dress, trousers));

        t1.start();
        t2.start();
    }
}

class MyRunnable1 implements Runnable {
    private final Dress dress;
    private final Trousers trousers;

    public MyRunnable1(Dress dress, Trousers trousers) {
        this.dress = dress;
        this.trousers = trousers;
    }

    @Override
    public void run() {
        synchronized (dress) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (trousers) {
                System.out.println("-------------");
            }
        }
    }
}

class MyRunnable2 implements Runnable {
    private final Dress dress;
    private final Trousers trousers;

    public MyRunnable2(Dress dress, Trousers trousers) {
        this.dress = dress;
        this.trousers = trousers;
    }

    @Override
    public void run() {
        synchronized (trousers) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (dress) {
                System.out.println("-------------");
            }
        }
    }
}

class Dress {

}

class Trousers {

}
