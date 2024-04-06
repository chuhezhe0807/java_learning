package gc.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * 先前环境：
 *      我们希望在一个对象被GC回收时，做一些清理工作。
 *          不推荐重写 finalize 方法 原因：
 *              1、不同于一般实例，finalize 实例执行垃圾回收的周期更长，性能更低
 *              2、finalize() 方法是单线程执行的，因此在实例数目较多的情况下可能引起阻塞
 *              3、finalize() 方法会被子类所继承，在子类不知道的情况下，又影响了子类的垃圾回收性能
 *          除了重写finalize方法外，还可以使用 PhantomReference 和 ReferenceQueue来实现相似的功能
 *
 * ClassName: PhantomReferenceTest
 * Package: thread.hellovolatile
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/31 22:48
 * @Version 1.0
 */
public class PhantomReferenceTest {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        // 对应于一个引用(new M()) 如果GC线程发现除了Reference实例以外，没有任何其他引用指向它，就把它放入该Reference实例相关联
        // 的ReferenceQueue中，在模拟的垃圾回收线程中，一直在对ReferenceQueue进行poll()操作如果有值则证明 相关联的实例对象已经没有其他引用了
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);

        new Thread(() -> {
            while (true) {
                // 一直添加数据，直到内存不够用
                LIST.add(new byte[1024 * 1024]);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }

                System.out.println(phantomReference.get());
            }
        }).start();

        // 模拟堆外内存专用的垃圾回收线程
        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();

                // ReferenceQueue 相关联的实例对象没有其他引用了
                if(poll != null) {
                    System.out.println("--- 虚引用对象被jvm回收了 ---" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
