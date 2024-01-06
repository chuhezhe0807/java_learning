package algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 归并排序
 *  整体就是一个简单递归，左边排好序、右边排好序 让其整体有序
 *  让其整体有序的过程里使用了 排序方法
 *  利用master公式求解时间复杂度(master公式 T(N) = a * T(N/b) + N^d)
 *      T(N) = 2 * T(N/2) + N
 *      log(2, 2) = 1 -> 时间复杂度 N*log(2, N) 由于每次循环会生成一个额外空间(help数组)，用完了就释放掉，所以额外空间复杂度为O(N)
 *  归并排序的实质
 *
 * ClassName: MergeSort
 * Package: algorithm.sort
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/24 23:16
 * @Version 1.0
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 4, 2, 9, 1, 3, 5};

        process(arr, 0, 7);

        System.out.println(Arrays.toString(arr));
    }

    public static void process(int[] arr, int l, int r) {
        if(l == r) {
            return;
        }

        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);

        merge(arr, l, mid, r);
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = mid + 1;

        while(p1 <= mid && p2 <= r) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while(p1 <= mid) { // p2 不小于等于 r 了，出现了数组越界的情况
            help[i++] = arr[p1++];
        }

        while(p2 <= r) {
            help[i++] = arr[p2++];
        }

        for(int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
    }

    @Test
    public void test01() {
        System.out.println(smallSum(new int[]{1, 1, 2, 3, 5, 2}));  // 2 + 4 + 7 + 2 = 15
    }

    // 小和问题 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和
    public static int smallSum(int[] arr) {
        if(null == arr || arr.length < 2) {
            return 0;
        }

        return smallSumProcess(arr, 0, arr.length - 1);
    }

    public static int smallSumProcess(int[] arr, int l, int r) {
        if(l == r) {
            return 0;
        }

        int mid = l + ((r - l) >> 1);

        return smallSumProcess(arr, l, mid)
                + smallSumProcess(arr, mid + 1, r)
                + smallSumMerge(arr, l, mid, r);
    }

    public static int smallSumMerge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int res = 0;

        while(p1 <= m && p2 <= r) {
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while(p1 <= m) {
            help[i++] = arr[p1++];
        }

        while(p2 <= r) {
            help[i++] = arr[p2++];
        }

        for(i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }

        return res;
    }
}
