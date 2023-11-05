package thread.hellovolatile;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * ClassName: CasAndUnsafe
 * Package: thread.cas
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/2 22:31
 * @Version 1.0
 */
public class synchronizedTest {
    private static int m = 0;

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);

        Object o = new Object();

        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                synchronized(o) { // 保证多个线程共用一个 o 对象即可
                    for(int j = 0; j < 10000; j++) {
                        m++;
                    }

                    latch.countDown();
                }
            });
        }

        Arrays.stream(threads).forEach(Thread::start);

        latch.await();

        System.out.println(m);
    }
}
