package thread.objectlayout;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName: Test
 * Package: thread.cas
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/25 22:56
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        AtomicInteger i = new AtomicInteger();
        i.incrementAndGet();

        Object o = new Object();

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

    }
}
