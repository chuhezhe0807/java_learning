package thread.lockupgrade.cachepadding;

/**
 * 缓存行对齐
 *
 * ClassName: CacheLinePaddingTest01
 * Package: thread.cas
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/28 21:36
 * @Version 1.0
 */
public class CacheLinePaddingTest01 {
    private static class T {
        // volatile 保证线程的可见性
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];

    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[0].x = i;
            }
        });

        Thread t2 = new Thread(() -> {
            for (long i = 0; i < 1000_0000L; i++) {
                arr[1].x = i;
            }
        });

        final long start = System.nanoTime();
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println((System.nanoTime() - start) / 100_0000); // 执行时间100ms左右
    }
}
