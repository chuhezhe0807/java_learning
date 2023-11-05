package thread.hellovolatile;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类中的value使用 volatile 关键字修饰
 * volatile 作用：1、保证线程的可见性（某个线程修改值后，通知其他线程重新读取该值） 2、禁止CPU指令重排
 *
 * ClassName: CasAtomicInteger
 * Package: thread.cas
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/2 23:11
 * @Version 1.0
 */
public class volatileTest {
    private static AtomicInteger m = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        for(int i = 0; i < 100; i++) {
            threads[i] = new Thread(() -> {
                for(int j = 0; j < 10000; j++) {
                    m.incrementAndGet();
                }

                latch.countDown();
            });
        }

        Arrays.stream(threads).forEach(Thread::start);

        latch.await();

        System.out.println(m);
    }
}
