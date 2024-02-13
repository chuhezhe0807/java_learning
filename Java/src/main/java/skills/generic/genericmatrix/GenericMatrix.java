package skills.generic.genericmatrix;

/**
 * ClassName: GenericMatrix
 * Package: generic.genericmatrix
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/9 22:32
 * @Version 1.0
 */
public abstract class GenericMatrix<E extends Number> {
    protected abstract E add(E o1, E o2);
    protected abstract E multiply(E o1, E o2);
    protected abstract E zero();

    @SuppressWarnings("unchecked")
    public E[][] addMatrix(E[][] matrix1, E[][] matrix2) { // matrix1 一维数组代表的是行，二维数组代表的是列
        if(matrix1.length != matrix2.length ||
                matrix1[0].length != matrix2[0].length) {
            throw new RuntimeException("The matrices do not have same size.");
        }

        E[][] result = (E[][]) new Number[matrix1.length][matrix1[0].length];

        // Perform addition
        for(int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                result[i][j] = add(matrix1[i][j], matrix2[i][j]);
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public E[][] multiplyMatrix(E[][] matrix1, E[][] matrix2) {
        // 两个矩阵相乘的条件：第一个矩阵的列数与第二个矩阵的行数相等才能相乘
        if(matrix1[0].length != matrix2.length) {
            throw new RuntimeException("The matrices do not have compatible size.");
        }

        // 两矩阵相乘的结果具有第一个矩阵的行数，第二个矩阵的列数
        E[][] result = (E[][]) new Number[matrix1.length][matrix2[0].length];

        for(int i = 0; i < result.length; i++) {
            for(int j = 0; j < result[i].length; j++) {
                result[i][j] = zero();

                loop1: for(int k = 0; k < matrix1[0].length; k++) {
                    // 行和列相乘，对应位置的值相乘，然后相加
                    // 每一个 loop1 都是行列相乘中的某一个值，乘完之后在累加起来
                    result[i][j] = add(result[i][j], multiply(matrix1[i][k], matrix2[k][j]));
                }
            }
        }

        return result;
    }

    // 打印矩阵的计算式
    public static void printResult(Number[][] m1, Number[][] m2, Number[][] m3, char operator) {
        for(int i = 0; i < m1.length; i++) {
            for(int j = 0; j < m1[0].length; j++) {
                System.out.print(" " + m1[i][j]);
            }

            if(i == m1.length / 2) {
                System.out.print("  " + operator + " ");
            }
            else {
                System.out.print("    ");
            }

            for(int j = 0; j < m2.length; j++) {
                System.out.print(" " + m2[i][j]);
            }

            if(i == m1.length / 2) {
                System.out.print("  =  ");
            }
            else {
                System.out.print("     ");
            }

            for(int j = 0; j < m3.length; j++) {
                System.out.print(m3[i][j] + " ");
            }

            System.out.println();
        }
    }
}
