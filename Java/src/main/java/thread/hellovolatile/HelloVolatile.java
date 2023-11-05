package thread.hellovolatile;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: HelloVolatile
 * Package: thread.hellovolatile
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/10/29 22:52
 * @Version 1.0
 */
public class HelloVolatile {
    /**
     * volatile 作用：1、保证线程的可见性 2、禁止指令重排
     * 1、保证线程的可见性
     *      一个线程对加了volatile关键字修饰的变量做了修改，需要立即写回该变量，另一个线程需要用到这个值的时候需要重新读一遍
     * 2、禁止指令重排
     *      CPU执行指令的顺序按照代码的顺序
     */

    volatile boolean running = true; // 对比是否添加Volatile关键字的情况

    void m() {
        System.out.println("m start.");

        while (running) {

        }

        System.out.println("m end");
    }

    public static void main(String[] args) {
        HelloVolatile helloVolatile = new HelloVolatile();

        new Thread(helloVolatile::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("here...");

        helloVolatile.running = false;
    }
}
