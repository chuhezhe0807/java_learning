package algorithm.loop;

/**
 * ClassName: PrimeNumberTest
 * Package: algorithm.loop
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/16 15:57
 * @Version 1.0
 */
public class PrimeNumberTest {
    public static void main(String[] args) {
        primeNumber(100);
    }

    // 时间复杂度O(n√n)
    public static void primeNumber(int num) {
        int squareRoot = 1;

        for(int i = 2; i <= num; i++) {
            boolean isPrime = true;

            // 实际上没有必要对每一个number来准确计算Math.sqrt(number)，只需要找出完全平方数
            if(squareRoot * squareRoot < num) {
                squareRoot++;
            }

//            for(int j = 2; j <= Math.sqrt(i); j++) {
            for(int j = 2; j < squareRoot; j++) {
                if(i % j == 0) {
                    isPrime = false;

                    break;
                }
            }

            if(isPrime) {
                System.out.print(i + " ");
            }
        }
    }
}
