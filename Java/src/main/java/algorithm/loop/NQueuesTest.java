package algorithm.loop;

/**
 * 回溯算法解决8皇后问题。、
 *
 * ClassName: NQueuesTest
 * Package: algorithm.loop
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/16 16:48
 * @Version 1.0
 */
public class NQueuesTest {
    public static void main(String[] args) {
        nQueues();
    }

    private static final int N = 4;
    private static int[] y; // 记录每列上皇后放的位置
    public static int count = 0; //   解的个数

    public static void nQueues() {
        count = 0;
        int x = 1;
        y = new int[N + 1];  // 初始化数组

        while (x > 0) {
            y[x]++; //为当前x位置找一个皇后的位置

            while ((y[x] <= N) && (!check(x))) {
                y[x]++; //找到合适的位置
            }

            if (y[x] <= N) {
                if (x == N) {   // 如果找到了一个放置皇后的完整方案，解+1，打印结果
                    count++;

                    print();
                }
                else {
                    x++;  // 还没找到完整方法，继续寻找下一个皇后位置。
                }
            }
            else {
                // 所有位置均不安全，回溯。
                y[x] = 0;
                x--;
            }
        }
    }

    //  测试合法性
    private static boolean check(int k) {
        for (int j = 1; j < k; j++) {
            if ((Math.abs(k - j) == Math.abs(y[j] - y[k])) || (y[j] == y[k])) {
                return false;
            }
        }

        return true;
    }

    // 显示结果
    private static void print() {
        System.out.println(count);

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                //如果该位置放了皇后则显示x
                System.out.print(j == y[i] ? "x" : "o");
            }

            System.out.println();
        }
    }
}
