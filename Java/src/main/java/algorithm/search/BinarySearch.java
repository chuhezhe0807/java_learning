package algorithm.search;

import org.junit.jupiter.api.Test;

/**
 * 二分法查找
 * ClassName: BinarySearch
 * Package: algorithm.search
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/23 15:48
 * @Version 1.0
 */
public class BinarySearch {
    public static int search(int[] sortedArr, int target) {
        if(null == sortedArr || 0 == sortedArr.length) {
            return -1;
        }
        else if(1 == sortedArr.length) {
            return 0;
        }

        int low = 0;
        int height = sortedArr.length - 1;

        while(low <= height) {
            int middleIndex = low + ((height - low) >> 1);

            if(sortedArr[middleIndex] == target) {
                return middleIndex;
            }
            else if(sortedArr[middleIndex] > target) {
                height = middleIndex - 1;
            }
            else if(sortedArr[middleIndex] < target) {
                low = middleIndex + 1;
            }
        }

        return -1;
    }

    // 在一个有序数组中，查找某个数是否存在
    @Test
    public void test01() {
        System.out.println(search(new int[]{1, 3, 5, 6, 7, 9}, 7));
    }

    // 在一个有序数组中，找到大于等于某个数最左侧的位置
    public static int nearLeftSearch(int[] sortedArr, int value) {
        if(null == sortedArr || sortedArr.length == 0) {
            return -1;
        }

        int index = -1;
        int low = 0;
        int height = sortedArr.length - 1;

        while(low <= height) {
            int middle = low + ((height - low) >> 1);

            if(sortedArr[middle] >= value) {
                index = middle;
                height = middle - 1;
            }
            else {
                low = middle + 1;
            }
        }

        return index;
    }

    @Test
    public void test02() {
        System.out.println(nearLeftSearch(new int[]{1, 2, 2, 2, 2, 4, 4, 4, 5}, 2));
    }

    // 局部最小值问题, 数组中存在一个数，比他左右两边的数都小，则称该数位局部最小值
    public static int localMinimumValue(int[] arr) {
        if(arr[0] < arr[1]) {
            return arr[0];
        }
        else if(arr[arr.length - 2] > arr[arr.length - 1]) {
            return arr[arr.length - 1];
        }

        int low = 1;
        int height = arr.length - 2;

        while(low <= height) {
            int mid = low + ((height - low) >> 1); // 不使用 int mid = (low + height) / 2; 是为了防止加法溢出

            if(arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return arr[mid];
            }
            else if(arr[mid] > arr[mid - 1]) { // 比左边的还大，那么要找的数就在左边
                height = mid - 1;
            }
            else if(arr[mid] > arr[mid + 1]) { // 同理，在右边
                low = mid + 1;
            }
        }

        return -1;
    }

    @Test
    public void test03() {
        System.out.println(localMinimumValue(new int[]{12,9,1,7,6,5,4,5}));
    }
}
