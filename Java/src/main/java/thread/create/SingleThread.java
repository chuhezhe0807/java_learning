package thread.create;

/**
 * 一个线程，就是程序的一条执行路径。
 */
public class SingleThread {
    public void method1(String str) {
        System.out.println(str);
    }

    public void method2(String str) {
        method1(str);
    }

    public static void main(String[] args) { //main线程
        SingleThread s = new SingleThread();
        s.method2("hello!");
    }

}
