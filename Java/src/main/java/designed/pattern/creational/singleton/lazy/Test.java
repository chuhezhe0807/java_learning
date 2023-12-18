package designed.pattern.creational.singleton.lazy;

/**
 * ClassName: Test
 * Package: designed.pattern.creational.singleton
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 20:35
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " " + instance.toString());
        });

        Thread t2 = new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " " + instance.toString());
        });

        Thread t3 = new Thread(() -> {
            LazySingleton instance = LazySingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + " " + instance.toString());
        });

        t1.start();
        t2.start();
        t3.start();

        System.out.println("当前程序结束...");
    }
}
