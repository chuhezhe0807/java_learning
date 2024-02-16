package algorithm.recursion;

import org.junit.jupiter.api.Test;

/**
 * 递归相关
 *  只要是满足 子问题等规模的递归都可以用master公式直接求解复杂度
 *  master公式 T(N) = a * T(N/b) + O(N^d)
 *  a -> 子问题调用的次数
 *  N/b -> （母问题可以分成多少的子问题）多少个子问题
 *  O(N^d) -> 除去递归部分的计算过程的时间复杂度(决策部分的时间复杂度)
 *
 *      a,b,d 三个系数确定后，即可确定该算法的时间复杂度  log(b, a) -> log以b为底的a
 *      log(b, a) > d -> 时间复杂度为O(N ^ log(b, a))
 *      log(b, a) = d -> 时间复杂度为O((N ^ d) * log(2, N))
 *      log(b, a) < d -> 时间复杂度为O(N ^ d)
 *
 * ClassName: GetMax
 * Package: algorithm.recursion
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/24 18:51
 * @Version 1.0
 */
public class GetMaxTest {
    public static int getMax(int[] arr) {
        return process(arr, 0, arr.length - 1);
    }

    // 使用递归方法 求arr[L...R]范围上的最大值
    // 此方法计算过程满足master公式，公式为 T(N) = 2 * T(N/2) + O(1) 所以 a: 2 b: 2 d: 0
    // 即 log(2, 2) > 0 时间复杂度为 O(N ^ log(2, 2)) == O(N)
    public static int process(int[] arr, int l, int r) {
        if(l == r) {
            return  arr[l];
        }

        int mid = l + ((r - l) >> 1);
        int leftMax = process(arr, l, mid);
        int rightMax = process(arr, mid + 1, r);

        return Math.max(leftMax, rightMax);
    }

    @Test
    public void test01() {
        System.out.println(getMax(new int[]{1, 2, 3, 4, 5, 6, 7}));
    }
}
