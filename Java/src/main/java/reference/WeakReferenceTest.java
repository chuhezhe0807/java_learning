package reference;

import java.lang.ref.WeakReference;

/**
 * ClassName: WeakReferenceTest
 * Package: thread.hellovolatile
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/31 22:21
 * @Version 1.0
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        // 如果threadLocal对象不是用了，tl被GC回收，Map:threadLocals中的key为弱引用，会直接被垃圾回收，
        // 此时key为null，但是在map中这一对键值对还存在，所以此时必须手动调用 tl.remove(),防止内存泄漏
        tl.remove();
    }
}
