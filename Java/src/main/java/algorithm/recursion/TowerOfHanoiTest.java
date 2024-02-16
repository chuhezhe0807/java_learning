package algorithm.recursion;

/**
 * 汉诺塔问题
 *      给定三根柱子，记为 A、B、C，其中A柱子上有n个盘子，从上到下编号为0到n-1，且上面的盘子一定比下面的盘子小。
 *      问：将A柱上的盘子经由B柱移动到C柱最少需要多少次？
 *
 *      解题思路：
 *          n个盘子上面的n-1个盘子（编号0~n-1）视为一个整体，我们需要将其移动到C柱（经过空的C柱）需要f(n-1)步
 *          （不考虑柱子编号，我们利用空柱子将这一部分移动过去，需要的步数相同）。此时仅仅需要1步将第n-1个盘子（编号为n-1）
 *          移动到C柱子，此时这个大盘子不影响我们移动剩余的n-1个盘子。那我们就可以经A柱，将其移动到C柱，又需f(n-1)步，此时n个盘子移动完毕。
 *          共需要：f(n) = 2 * f(n - 1) + 1 次（这便是移动n个盘子到目标状态的最小步数）
 *
 *      为什么通过这个式子计算出来的就一定是最小步数呢？
 *          我们已经规定f(n - 1)为移动n-1个盘子到目标状态的最小步数，且两次移动这n-1个盘子为必需的，否则第n个盘子无法移动到
 *          C柱，并且移动它必需1步，则 f(n) = 2 * f(n - 1) + 1 中无多余的移动，就是最小步数。
 *
 * ClassName: TowerOfHanoiTest
 * Package: algorithm.recursion
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/16 11:44
 * @Version 1.0
 */
public class TowerOfHanoiTest {
    public static void main(String[] args) {
        moveDisks(3, 'A', 'C', 'B');
    }

    public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {
        if(n == 1) {
            System.out.println("move disk" + n + " from " + fromTower + " to " + toTower);
        }
        else {
            moveDisks(n - 1, fromTower, auxTower, toTower);
            System.out.println("move disk" + n + " from " + fromTower + " to " + toTower);
            moveDisks(n - 1, auxTower, toTower, fromTower);
        }
    }
}
