package thread.create.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: ThreadTest
 * Package: thread.create.thread
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/27 15:38
 * @Version 1.0
 */
public class ThreadTest {
    public static void main(String[] args) throws InterruptedException {
        A a = new A();

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(() -> {
            for(int i = 1; i <= 10000000; i++) {
                a.increase();
            }
        });
        t1.start();

        for(int i = 1; i <= 10000000; i++) {
            a.increase();
        }

        t1.join();

        long end = System.currentTimeMillis();
        System.out.printf("%sms%n", end - start);
        System.out.println(a.getNum());
    }
}

class A {
    private int num;
    private AtomicInteger atomicInteger = new AtomicInteger(); // cas 无锁、轻量级锁、自旋锁

    // synchronized(this) 比较的耗费性能 耗时311ms左右 
    public void increase() {
        synchronized (this) {
            num++;
        }
//        atomicInteger.incrementAndGet(); // 使用AtomicInteger 原子类耗时182ms左右
    }

    public int getNum() {
        return num;
//        return atomicInteger.get();
    }
}
