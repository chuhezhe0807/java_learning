package algorithm.bitwiseoperation;

/**
 * ClassName: EventTimesOddTimes
 * Package: algorithm.bitwiseoperation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/22 22:11
 * @Version 1.0
 */
public class EventTimesOddTimes {
    // 找出一组数中，两个只出现了奇数次的数（其他数都出现了偶数次）
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;

        for(int cur : arr) {
            eor  = eor ^ cur;
        }

        // 假设只出现了奇数次的两个数分别位a, b
        // 此时 eor = a ^ b 且 eor != 0
        int rightOne = eor & (~eor + 1); // 提取出最右边的1

        int a = 0;
        for(int cur : arr) {
            if((cur & rightOne) == 0) { // 由于a和b不相等且其余数都出现了偶数次，所以异或完成后的值一定是a或b
                a = a ^ cur;
            }
        }

        System.out.println(a + ", " + (eor ^ a));
    }

    public static void main(String[] args) {
        printOddTimesNum2(new int[]{1, 1, 2, 3, 3, 4});
    }
}
