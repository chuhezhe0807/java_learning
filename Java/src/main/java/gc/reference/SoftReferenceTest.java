package gc.reference;

import org.junit.jupiter.api.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * ClassName: SoftReference
 * Package: thread.hellovolatile
 * Description:
 *
 * 软引用是一种相对强引用弱化了一些的引用，需要使用 java.lang.ref.SoftReference 类来实现
 * eg:
 *       String str = new String("aaa"); // 强引用
 *       SoftReference<String> softRef = new SoftReference<>(str); // 软引用
 *
 *  如果一个对象只具有软引用，则内存空间足够，垃圾回收器就不会回收它，如果内存空间不足了，就会回收这些对象的内存
 *  软引用可以和一个引用队列(ReferenceQueue)联合使用，如果软引用所引用的对象被垃圾回收，java虚拟机就会把这个软引用加入到与之关联的引用队列中
 *
 * @Author Chuhezhe
 * @Create 2023/10/31 21:58
 * @Version 1.0
 */
public class SoftReferenceTest {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);

        System.out.println(m.get());
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(m.get());

        // 再分配一个数组，heap将装不下，这时候系统会垃圾回收，先回收一次，如果不够，会把软引用干掉
        byte[] b = new byte[1024 * 1024 * 15];
        System.out.println(m.get());
    }

    @Test
    public void test01() throws InterruptedException {
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1, queue);
        o1 = null; // 清除强引用

        System.gc();

        System.out.println("GC 之后的值： " + softReference.get()); // 对象依然存在

        byte[] b = new byte[1024 * 1024 * 100]; // 申请较大的内存空间，使得使用率达到阈值，强迫gc

        Reference<?> remove = queue.remove(); // 如果 o1 被回收，则软引用会进入引用队列
        if(remove != null) {
            System.out.println("对象已被回收 " + remove.get()); // null，内存不足了，软引用被回收了
        }
    }
}
