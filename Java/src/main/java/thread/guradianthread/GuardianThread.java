package thread.guradianthread;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: BakDataThread
 * Package: thread.guradianthread
 * Description:
 *
 *
    java 中线程分为两大类
        1、用户线程
        2、守护线程(后台线程) 垃圾回收线程属于守护线程

    守护线程一般是一个死循环，所有的用户线程结束，守护线程自动结束
 *
 * @Author Chuhezhe
 * @Create 2024/1/22 21:35
 * @Version 1.0
 */
public class GuardianThread {
    public static void main(String[] args) {
        BakDataThread t = new BakDataThread();
        t.setName("备份数据的线程");
        t.setDaemon(true); // 设置线程为守护线程
        t.start();

        // 主线程：主线程是用户线程
        for(int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " --> " + i);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class BakDataThread extends Thread {
    private int i = 0;

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " --> " + ++i);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
