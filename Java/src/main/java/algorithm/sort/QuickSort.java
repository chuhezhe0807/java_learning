package algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 *  时间复杂度 O(nlogn)
 *      最差情况下，划分由n个元素构成的数组需要进行n次比较和n次移动因此划分时间为O(n)，最差情况下基准元素每次会将数组划分为一个
 *          较大的子数组和另外一个空数组。这个较大的子数组的的大小是在划分前的子数组的大小减一，该算法需要(n - 1) + (n - 2) + .... + 2 + 1 = O(n^2)时间
 *      最佳情况下，基准元素每次会将数组划分为两个大致等规模的数组，此时T(N) = T(N / 2) + T(N / 2) + n
 *      (T(N / 2) -> 在两个子数组上面进行递归的快速排序，n -> 分区的时间) 符合master公式 时间复杂度为O(nlogn)
 *
 *      平均情况下，基准元素不会每次将数组分为等规模的两部分或者一个是空数组的部分。从统计上说，两部分的规模会非常相近，因此平均时间是O(nlogn)
 *  工作机制：在数组中选择一个称为基准的元素，将数组分为两部分，使得第一部分中的所有元素都小于等于基准元素
 *              而第二部分中的元素都大于等于基准元素。对这两部分都递归的应用快速排序。
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
            // 将数组最后一个值与arr数组中任意一个元素交换位置，就是随机选择一个值为划分值arr[r]
            // 注释掉下面这一行就是默认以arr[r]做划分
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partition(arr, l, r);
            quickSort(arr, l, p[0] - 1); // < 区
            quickSort(arr, p[1] + 1, r); // > 区
        }
    }

    // 这是一个处理arr[l..r]的函数
    // 默认以arr[r]做划分，arr[r] -> p       <p   ==p  >p
    // arr数组分为arr[r]和其他的区域，其他的区域又被分为两个区域，小于arr[r]的放在左边，大于的放在右边
    // 然后arr[r]和大于区域的左边界做交换，相当于说小于arr[r]区域扩充了，小于区域的最后一个数一定是arr[r]
    // 此时整个数组中arr[r]这个数就可以认为已经排好序了，大于区域和小于区域再重复这一操作，就可以让整个数组排好序了。
    // 返回等于区域（左边界，右边界），所以返回一个长度为2的数组res
    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1; // 小于arr[r]区域右边界
        int more = r; // 大于arr[r]区域左边界

        while(l < more) { // l标识当前数的位置 arr[r] -> 划分值  while循环的限制条件为小于arr[r]区域的右边界一定小于大于arr[r]区域的左边界
            if(arr[l] < arr[r]) { // 当前数 < 划分值，小于arr[r]区域的右边界向右扩充，并交换当前值和小于区域的右边界值，当前值对应的索引++
                swap(arr, ++less, l++);
            }
            else if(arr[l] > arr[r]) { // 当前数 > 划分值，大于arr[r]区域的左边界向左扩充，并交换当前值和大于区域的右边界值
                swap(arr, --more, l);
            }
            else {
                l++;
            }
        }

        swap(arr, more, r); // arr[r]和大于区域的左边界交换位置

        return new int[]{less + 1, more};
    }

    // 使用异或方式交换数组中两个索引位置对应的值，需要注意这两个索引不能一样，如果一样会都被置为0
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
