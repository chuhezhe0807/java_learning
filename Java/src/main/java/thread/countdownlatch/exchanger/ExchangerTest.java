package thread.countdownlatch.exchanger;

import java.util.concurrent.Exchanger;

/**
 * Exchanger 主要用于线程间的数据交换，交换的目的在于比较两份数据是否一致
 *
 * ClassName: ExchangerTest
 * Package: thread.countdownlatch.exchanger
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/19 23:20
 * @Version 1.0
 */
public class ExchangerTest {
    public static void main(String[] args) {
        // 交换器
        Exchanger<String> exchanger = new Exchanger<>();

        // 线程A
        new Thread(() -> {
            try {
                String A = "apple";
                // 与另一个线程进行交换，并得到另一个线程的数据
                String B = exchanger.exchange(A);
                System.out.println("A 交换过程 " + A + "--->" + B);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        // 线程B
        new Thread(() -> {
            try {
                String B = "banana";
                // 与另一个线程进行交换，并得到另一个线程的数据
                String A = exchanger.exchange(B);
                System.out.println("B 交换过程 " + B + "--->" + A);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
