package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
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
