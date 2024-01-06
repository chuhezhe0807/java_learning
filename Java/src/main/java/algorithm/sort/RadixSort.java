package algorithm.sort;

import java.util.Arrays;

/**
 * 基数排序（不基于比较的排序）
 *
 * ClassName: RadixSort
 * Package: algorithm.sort
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/6 15:34
 * @Version 1.0
 */
public class RadixSort {
    public static void main(String[] args) {
        radixSort(new int[]{2, 3, 1, 5, 4});
    }

    // 只能排非负的数
    public static void radixSort(int[] arr) {
        if(null == arr || arr.length < 2) {
            return;
        }

        radixSort(arr, 0, arr.length - 1, maxBits(arr));

        System.out.println(Arrays.toString(arr));
    }

    public static int maxBits(int[] arr) {
        int res = 0;
        int max = Integer.MIN_VALUE;

        for (int j : arr) {
            max = Math.max(max, j);
        }

        while(max != 0) {
            res++;
            max /= 10;
        }

        return res;
    }

    // arr[begin...end] 排序
    public static void radixSort(int[] arr, int l, int r, int digit) { // digit 从 l 索引 到 r 索引，这一批数字中最大值有几个十进制位
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数就准备多少个辅助空间
        int[] bucket = new int[r - l + 1];

        for(int d = 1; d < digit; d++) { // 有多少位就进出几次
            // 10个空间
            // count[0] 当前位（d位）是0的数字有多少个
            // count[1] 当前位（d位）是0和1的数字有多少个
            // count[2] 当前位（d位）是0、1和2的数字有多少个
            // count[i] 当前位（d位）是0~i的数字有多少个
            int[] count = new int[radix];

            for(i = l; i <= r; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }

            for(i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }

            for(i = r; i >= l; i--) {
                j = getDigit(arr[i], d);
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }

            for(i = l, j = 0; i <= r; i++, j++) {
                arr[i] = bucket[j];
            }
        }
    }

    public static int getDigit(int x, int d) {
        return ((x / ((int) Math.pow(10, d - 1))) % 10);
    }
}
