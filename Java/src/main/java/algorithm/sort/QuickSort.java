package algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 *  时间复杂度
 *
 * ClassName: QuickSort
 * Package: algorithm.sort
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/31 21:40
 * @Version 1.0
 */
public class QuickSort {
    public static void main(String[] args) {
        quickSort(new int[]{4, 2, 1, 4, 5, 3});
    }

    public static void quickSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        quickSort(arr, 0, arr.length - 1);

        System.out.println(Arrays.toString(arr));
    }

    // arr[l...r] 排序
    public static void quickSort(int[] arr, int l, int r) {
        if(l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1); // < 区
            quickSort(arr, p[1] + 1, r); // > 区
        }
    }

    // 这是一个处理arr[l..r]的函数
    // 默认以arr[r]做划分，arr[r] -> p       <p   ==p  >p
    // 返回等于区域（左边界，右边界），所以返回一个长度为2的数组res
    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1; // < 区右边界
        int more = r; // > 区左边界

        while(l < more) { // l标识当前数的位置 arr[r] -> 划分值
            if(arr[l] < arr[r]) { // 当前数 < 划分值
                swap(arr, ++less, l++);
            }
            else if(arr[l] > arr[r]) { // 当前数 > 划分值
                swap(arr, --more, l);
            }
            else {
                l++;
            }
        }

        swap(arr, more, r);

        return new int[]{less + 1, more};
    }

    // 使用异或方式交换数组中两个索引位置对应的值，需要注意这两个索引不能一样，如果一样会都被置为0
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
