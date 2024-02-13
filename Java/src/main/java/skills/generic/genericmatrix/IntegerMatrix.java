package skills.generic.genericmatrix;

/**
 * ClassName: IntegerMatrix
 * Package: generic.genericmatrix
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/10 11:47
 * @Version 1.0
 */
public class IntegerMatrix extends GenericMatrix<Integer> {
    @Override
    protected Integer add(Integer o1, Integer o2) {
        return o1 + o2;
    }

    @Override
    protected Integer multiply(Integer o1, Integer o2) {
        return o1 * o2;
    }

    @Override
    protected Integer zero() {
        return 0;
    }
}
