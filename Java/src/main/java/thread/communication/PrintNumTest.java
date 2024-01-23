package thread.communication;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: PrintNumTest
 * Package: thread.communication
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/22 22:36
 * @Version 1.0
 */
public class PrintNumTest {
    public static void main(String[] args) {
        PrintNumThread printNumThread = new PrintNumThread();

        Thread t1 = new Thread(printNumThread, "thread-1");
        Thread t2 = new Thread(printNumThread, "thread-2");

        t1.start();
        t2.start();
    }
}

class PrintNumThread extends Thread {
    private int num = 1;
    private final Object obj = new Object();

    @Override
    public void run() {
        while(true) {
            synchronized(obj) {
                obj.notify();

                if(num <= 100) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + " --> " + ++num);

                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    break;
                }
            }
        }
    }
}
