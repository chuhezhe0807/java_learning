package thread.communication;

import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者
 *
 * ClassName: ProducerConsumer
 * Package: thread.communication
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/22 23:12
 * @Version 1.0
 */
public class ProducerConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer1 = new Producer(clerk);
        Consumer consumer1 = new Consumer(clerk);
        Consumer consumer2 = new Consumer(clerk);

        Thread t1 = new Thread(producer1, "producer1");
        Thread t2 = new Thread(consumer1, "consumer1");
        Thread t3 = new Thread(consumer2, "consumer2");

        t1.start();
        t2.start();
        t3.start();
    }
}

class Clerk {
    private int productNum = 0;

    // 生产产品
    public synchronized void addProduct() {
        if(productNum >= 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println(Thread.currentThread().getName() + " 生产了第 " + ++productNum + "个产品");

            // 唤醒
            notifyAll();
        }
    }

    // 卖出产品
    public synchronized void minusProduct() {
        if(productNum <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println(Thread.currentThread().getName() + " 卖出了第 " + productNum-- + "个产品");

            // 唤醒
            notifyAll();
        }
    }
}

class Producer extends Thread {
    private final Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("生产者开始生产产品....");

            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.addProduct();
        }
    }
}

class Consumer extends Thread {
    private final Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println("消费者开始消费产品....");

            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clerk.minusProduct();
        }
    }
}
