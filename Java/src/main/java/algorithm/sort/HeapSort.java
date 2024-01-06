package algorithm.sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 堆
 *  1、对结构就使用数组实现的完全二叉树结构
 *  2、完全二叉树中如果每棵子树的最大值都在顶部就是大根堆
 *  3、完全二叉树中如果每棵子树的最小值都在顶部就是小根堆
 *  4、优先级队列结构，就是堆结构
 *
 * 堆排序
 *
 * ClassName: HeapSort
 * Package: algorithm.sort
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/1/4 22:49
 * @Version 1.0
 */
public class HeapSort {
    public static void main(String[] args) {
        heapSort(new int[]{2, 3, 1, 5, 4});
    }

    public static void heapSort(int[] arr) {
        if(null == arr || arr.length < 2) {
            return;
        }

        for(int i = 0; i < arr.length; i++) { // O(N)
            heapInsert(arr, i); // O(logN)
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);

        while(heapSize > 0) { // O(N)
            heapify(arr, 0, heapSize); // O(logN)
            swap(arr, 0, --heapSize); // O(1)
        }

        System.out.println(Arrays.toString(arr));
    }

    // 某个数现在处在index的位置，往上继续移动
    public static void heapInsert(int[] arr, int index) {
        while(arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1; // 左孩子的下标

        while(left < heapSize) { // 下方还有孩子的时候
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left  + 1 : left;

            // 符合较大的孩子之间，谁的值大，把下标给largest
            largest = arr[largest] > arr[index] ? largest : index;

            if(largest == index) {
                break;
            }

            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 对于一个几乎排好序的数组(从原始数组到排好序，数组中每一个元素需要移动的索引不超过k)
    public static void sortedArrDistanceLessK(int[] arr, int k) {
        // 默认小根堆 (优先级队列就是堆)
        PriorityQueue<Integer> heap = new PriorityQueue<>(); // 不传入任何参数的话就是小根堆
        int index = 0;

        for(; index <= Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }

        int i = 0;
        for (; index < arr.length; i++, index++) {
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }

        while(!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }

    @Test
    public void test01() {
        // PriorityQueues实现的 小根堆/小根堆
//        PriorityQueue<Integer> heap = new PriorityQueue<>();  // 不指定比较器，默认为小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) -> o2 - o1); // 大根堆

        heap.add(2);
        heap.add(1);
        heap.add(-1);
        heap.add(6);
        heap.add(3);

        while(!heap.isEmpty()) {
            System.out.println(heap.poll());
        }
    }
}
