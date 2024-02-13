package skills.generic.genericmatrix;

import org.junit.jupiter.api.Test;

/**
 * ClassName: GenericMatrixTest
 * Package: generic.genericmatrix
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/10 12:16
 * @Version 1.0
 */
public class GenericMatrixTest {
    // 测试 IntegerMatrix
    @Test
    public void test01() {
        Integer[][] m1 = new Integer[][]{{1, 2, 3}, {4, 5, 6}, {1, 1, 1}};
        Integer[][] m2 = new Integer[][]{{1, 1, 1}, {2, 2, 2}, {0, 0, 0}};

        IntegerMatrix integerMatrix = new IntegerMatrix();

        System.out.println("\nm1 + m2 is");

        GenericMatrix.printResult(m1, m2, integerMatrix.addMatrix(m1, m2), '+');

        System.out.println("\nm1 * m2 is ");
        GenericMatrix.printResult(m1, m2, integerMatrix.multiplyMatrix(m1, m2), '*');
    }

    // 测试 RationalMatrix
    @Test
    public void test02() {
        Rational[][] m1 = new Rational[3][3];
        Rational[][] m2 = new Rational[3][3];

        for(int i = 0; i < m1.length; i++) {
            for(int j = 0; j < m1[0].length; j++) {
                m1[i][j] = new Rational(i + 1, j + 5);
                m2[i][j] = new Rational(i + 1, j + 6);
            }
        }

        RationalMatrix rationalMatrix = new RationalMatrix();
        System.out.println("\nm1 + m2 is ");
        GenericMatrix.printResult(m1, m2, rationalMatrix.addMatrix(m1, m2), '+');

        System.out.println("\nm1 * m2 is ");
        GenericMatrix.printResult(m1, m2, rationalMatrix.multiplyMatrix(m1, m2), '*');
    }
}
