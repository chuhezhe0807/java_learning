package gc.reference;

import org.junit.jupiter.api.Test;

/**
 * ClassName: StrongReferenceTest
 * Package: gc.reference
 * Description:
 *
 * 强引用
 * 如果一个对象具有强引用，垃圾回收器不会回收该对象，当内存空间不足时，JVM宁愿抛出 OutOfMemoryException
 * 如果请引用对象不使用时，需要弱化从而使GC能够回收，如显示地设置此对象为null，此时GC认为该对象不存在引用，就可以回收此对象
 *
 * @Author Chuhezhe
 * @Create 2024/6/2 14:05
 * @Version 1.0
 */
public class StrongReferenceTest {
    @Test
    public void test01() {
        Object o1 = new Object();
        Object o2 = o1;
        o1 = null;

        System.gc();

        System.out.println(o1); // null
        System.out.println(o2); // java.lang.Object@e320068 尽管 o1 已经被回收，但是 o2 强引用 o1 一直存在，所以不会被回收
    }
}
