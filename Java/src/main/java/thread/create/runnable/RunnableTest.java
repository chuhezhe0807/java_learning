package thread.create.runnable;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: RunnableTest
 * Package: thread.create.runnable
 * Description:
 *
 * Java 中有三大变量
 * 实例变量：在堆中 堆只有一个
 * 静态变量：在方法区    方法区只有一个
 * 局部变量：在栈中
 *
 *      局部变量永远都不存在线程安全问题
 *          1、因为局部变量不共享，局部变量在栈中，一个线程一个栈
 *      堆和方法区都是多线程共享的，所以可能存在线程安全问题
 *
 * @Author Chuhezhe
 * @Create 2024/1/21 19:33
 * @Version 1.0
 */
public class RunnableTest {
    // 中断线程的sleep方法
    @Test
    public void test01() {
        Thread t = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " -->" + "begin...");

            // run() 方法中的异常只能try-catch 不能 throws，因为run() 方法在父类中没有抛出任何异常，子类不能比父类抛出更多的异常
            try {
                TimeUnit.DAYS.sleep(365);
            } catch (InterruptedException e) {
                System.out.println("线程 sleep 被中断了");
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " -->" + "end...");
        });
        t.setName("Thread-t");
        t.start();

        // 主线程sleep 5秒，模拟执行任务时间
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 中断线程t的sleep
        t.interrupt();
    }

    // 合理的结束一个线程的方法
    @Test
    public void test02() {
        class MyThread extends Thread {
            public boolean run = true;

            @Override
            public void run() {
                for(int i = 0; i <= 10; i++) {
                    if(run) {
                        System.out.println(Thread.currentThread().getName() + " --> " + i);

                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        // do something...

                        // 终止当前线程
                        System.out.println("线程被终止了..");
                        return;
                    }
                }
            }
        }
        MyThread t = new MyThread();
        t.setName("Thread-t");
        t.start();

        // 主线程sleep 5秒，模拟执行任务时间
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 中断线程t
        t.run = false;
    }

    // join 方法
    @Test
    public void test03() {
        Thread t = new Thread(() -> {
            System.out.println("run....");
        });

        try {
            t.join(); // 当前线程进入阻塞，开始执行t线程，直到t线程结束，当前线程才可以继续
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // yield 方法 让位，让线程暂停，回到就绪状态，让给其他线程
    // 注意：并不是每一次都让成功，有可能它又抢到时间片了
    @Test
    public void test04() {
        Thread t = new Thread(() -> {
            for(int i = 1; i <= 10000; i++) {
                if(i % 100 == 0) {
                    Thread.yield(); // 每隔2个让位一次，当前线程暂停一下，让给主线程
                }

                System.out.println(Thread.currentThread().getName() + " --> " + i);
            }
        });
        t.setName("Thread-t");
        t.start();

        for(int i = 1; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName() + " --> " + i);
        }
    }
}
