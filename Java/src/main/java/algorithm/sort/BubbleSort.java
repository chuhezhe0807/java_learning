package algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *      时间复杂度O(n^2)
 *
 * ClassName: BubbleSort
 * Package: algorithm.sort
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/17 12:10
 * @Version 1.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        bubble0(new int[]{4, 2, 1, 4, 5, 3});
    }

    public static void bubble(int[] arr) {
        if(null == arr || arr.length < 2) {
            return;
        }

        // 在每一次遍历中，比较连续相邻的元素
        for(int i = 1; i < arr.length; i++) {
            for(int j = 0; j < arr.length - i; j++) {
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    // 如果某次遍历中没有发生交换，那么就不必进行下一次遍历因为所有元素都已经排好序了。
    public static void bubble0(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }

        boolean needNextPass = true;

        for(int i = 1; i < arr.length && needNextPass; i++) {
            needNextPass = false;

            for(int j = 0; j < arr.length - i; j++) {
                if(arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    needNextPass = true;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }
}
