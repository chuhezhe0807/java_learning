package algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 选择排序
 *  时间复杂度 O(N²)
 *
 * ClassName: InsertionSort
 * Package: algorithm.sort
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/23 15:06
 * @Version 1.0
 */
public class InsertionSort {
    public void insertSort(int[] arr) {
        if(null == arr || arr.length < 2) {
            return;
        }

        for(int i = 1; i < arr.length; i++) {
            for(int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    @Test
    public void test01() {
        insertSort(new int[]{4, 2, 1, 4, 5, 3});
    }
}
