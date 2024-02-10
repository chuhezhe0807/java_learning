package gc.reference;

import java.lang.ref.SoftReference;

/**
 * ClassName: SoftReference
 * Package: thread.hellovolatile
 * Description:
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
}
