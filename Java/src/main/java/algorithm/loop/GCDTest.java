package algorithm.loop;

/**
 * 求两个整数的最大公约数
 *
 * ClassName: GCDTest
 * Package: algorithm.loop
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/16 13:54
 * @Version 1.0
 */
public class GCDTest {
    public static void main(String[] args) {
//        System.out.println(gcd(25, 125));
        System.out.println(improvedgcd1(2525, 125));
    }

    // 假定m<n
    public static long gcd(long m, long n) {
       long gcd = 1;

       for(long i = m; i >= 2; i--) {
           if(n % i == 0 && m % i == 0) {
               gcd = i;

               break;
           }
       }

       return gcd;
    }

    // 因为数字n的除数不可能比n/2大，所以可以改进该算法
    public static long improvedgcd(long m, long n) {
        if(n % m == 0) { // 考虑n是m的整数倍的情况。
            return m;
        }

        long gcd = 1;

        for(long i = m / 2; i >= 2; i--) {
            if(n % i == 0 && m % i == 0) {
                gcd = i;

                break;
            }
        }

        return gcd;
    }

    // 上面的算法最坏的情况下时间复杂度仍然是O(n)
    // 假设n%m = r, 那么 n = qm + r, 这里的q是n/m的商。能整除m和n的任意数字都必须也能够整除r。所以 gcd(m, n) 和 gcd(m, r)是一样的
    // 算法时间复杂度为O(logn)
    public static long improvedgcd1(int m, int n) {
        if(m % n == 0) {
            return n;
        }
        else {
            return improvedgcd1(n, m % n);
        }
    }
}
