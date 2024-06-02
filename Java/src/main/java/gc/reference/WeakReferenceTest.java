package gc.reference;

import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * ClassName: WeakReferenceTest
 * Package: thread.hellovolatile
 * Description:
 *  弱引用
 *      不管内存是否足够，只要发生GC就会被回收
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
        // 如果threadLocal对象不使用了，tl被GC回收，Map:threadLocals中的key为弱引用，会直接被垃圾回收，
        // 此时key为null，但是在map中这一对键值对还存在，所以此时必须手动调用 tl.remove(),防止内存泄漏
        tl.remove();
    }

    // HashMap
    @Test
    public void test01() {
        HashMap<String, String> map = new HashMap<>();
        String key = new String("key");
        String value = new String("value");

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.gc();

        System.out.println(map); // {key=value}
    }

    // WeakHashMap
    @Test
    public void test02() {
        WeakHashMap<String, String> weakMap = new WeakHashMap<>();
        String key1 = new String("key");
        String value1 = new String("value");

        weakMap.put(key1, value1);
        System.out.println(weakMap);

        key1 = null;
        System.gc();

        System.out.println(weakMap); // {}
    }
}
