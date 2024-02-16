package algorithm.recursion;

/**
 * fibonacciRecursion时间复杂度
 *      T(n) = T(n - 1) + T(n - 2) + 1
 *           <= 2T(n - 1) + 1
 *           = 2(2T(n - 2) + 1) + 1
 *           = 2(2(2T(n - 2) + 1) + 1) + 1
 *           = 2^n-1 + 2^n-2 + .... + 2 + 1
 *           = 2^n-1 - 1
 *           = O(2^n)
 *
 *      精确值
 *      递归的斐波那契数列通项公式为 T(n) = √5/5（（（1 + √5）/2）^n -（（1 - √5）/2）^n）
 *      所以时间复杂度为O(（1 + √5）/2）^n)
 *
 * ClassName: FibonacciTest
 * Package: algorithm.recursion
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/16 13:32
 * @Version 1.0
 */
public class FibonacciTest {
    public static void main(String[] args) {
        System.out.println(fibonacciLoop(6));
    }

    public static long fibonacciRecursion(long n) {
        if(n == 1 || n == 2) {
            return 1;
        }

        return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
    }

    // 使用了动态编程的方法。
    // 动态编程是通过解决子问题，然后将子问题的结果结合来获得整个问题的解的过程。
    public static long fibonacciLoop(long n) { // 时间复杂度 O(n)
        long f0 = 0; // for fib(0)
        long f1 = 1; // for fib(1)
        long f2 = 1; // for fib(2)

        if(n == 0) {
            return f0;
        }
        else if(n == 1) {
            return f1;
        }
        else if(n == 2) {
            return f2;
        }

        for(int i = 3; i <= n; i++) {
            f0 = f1;
            f1 = f2;
            f2 = f0 + f1;
        }

        return f2;
    }
}
