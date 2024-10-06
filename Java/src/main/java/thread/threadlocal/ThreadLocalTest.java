package thread.threadlocal;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * ClassName: ThreadLocalTest
 * Package: thread.threadlocal
 * Description:
 *
 *      Object@sleep 和 LockSupport@parkNanos 都会上线程暂停，但是sleep不会释放资源，而parkNanos会释放资源
 *
 *      {@link ThreadLocal.ThreadLocalMap#getEntry} 中的计算下标的过程 int i = key.threadLocalHashCode & (table.length - 1);
 *      是在取模，eg: a % b == a & (b - 1)
 *
 *      ThreadLocalMap的key为什么要设置成弱引用？
 *          key有两条引用链
 *              1、Thread引用 -> Thread对象 -> ThreadLocalMap对象 -> ThreadLocalMap#Entry -> key
 *              2、ThreadLocal对象引用 -> ThreadLocal对象 -> key
 *          由于线程生命周期较长且一般采用线程池，所以线程一般不会被销毁，Thread引用也就不会断开。
 *          当手动将ThreadLocal对象引用置为null后，第二条引用就剩下 ThreadLocal对象 -> key 之间的引用，由于上述原因key不会被回收，
 *          所以ThreadLocal对象一直也不会被回收。这时候如果将ThreadLocal对象 -> key 之间的引用改为弱引用，那么ThreadLocal对象
 *          身上就只有一个弱引用，在下一次GC的时候ThreadLocal对象就会被回收。
 *
 *      为什么不讲value设置为弱引用？
 *          value没有类似key的 ThreadLocal对象 -> key 之间的引用，所以如果设置为弱引用在value没有被业务代码中其他引用的话就会被GC回收，
 *          导致出现ThreadLocalMap中key不为空，value为空的场景
 *
 *
 * @Author Chuhezhe
 * @Create 2024/10/6 12:52
 * @Version 1.0
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocalStr = new ThreadLocal<>();
    private static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    // 当前线程共享，其他线程不共享
    @Test
    public void testShouldOtherThreadAccessThreadLocal() throws InterruptedException {
        new Thread(() -> {
            threadLocalStr.set("hello..");

            System.out.println(Thread.currentThread().getName() + " " + threadLocalStr.get());
        }).start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " " + threadLocalStr.get());
        }).start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println(Thread.currentThread().getName() + " " + threadLocalStr.get());
    }

    // 父子线程共享ThreadLocal需要使用 InheritableThreadLocal
    // 一旦子线程被创建以后，再操作父线程中的ThreadLocal变量，那么子线程是不能感知的。
    @Test
    public void testShare() {
        threadLocalStr.set("hello...");
        inheritableThreadLocal.set("hello... from inheritableThreadLocal");

        LockSupport.parkNanos(1000 * 1000 * 1000L); // 等待1秒

        new Thread(() -> {
            // 拿不到父线程设置的值
            System.out.println(Thread.currentThread().getName() + " " + threadLocalStr.get());
            // 可以拿到
            System.out.println(Thread.currentThread().getName() + " " + inheritableThreadLocal.get());
        }).start();
    }

    // Thread.sleep 为什么要抛出中断异常？
    //      代码需要有响应中断的能力
    @Test
    public void testInterruptedException() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) { // 抛出异常的同时，线程中断的状态会被清除
            // 此时线程中断状态已经被清除，如果后续有线程中断状态的判断需要在这里手动中断一次
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        // 如果线程没有被中断就执行一些逻辑
        if(!Thread.currentThread().isInterrupted()) {
            // do something.
        }
    }
}
