package algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 选择排序
 *  时间复杂度 O(n²) 额外空间复杂度 O(1)，仅使用有限几个变量
 *
 * ClassName: SelectionSort
 * Package: algorithm.sort
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/20 23:39
 * @Version 1.0
 */
public class SelectionSort {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void selectionSort(int[] arr) {
        if(null == arr || arr.length < 2) {
            return;
        }

        for(int i = 0; i < arr.length - 1; i++) { // i ~ N-1
            int minIndex = i; // 额外的变量

            for(int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] < arr[j] ? minIndex : j;
            }

            if(minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{5, 3, 2, 4, 1};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test
    public void test01() {
        int a = 11;
        int b = 22;

        a = a ^ b; // ^ 位运算异或操作符 相同为0，不同为1
        b = a ^ b;
        a = a ^ b;

        System.out.println(a);
        System.out.println(b);
    }
}
