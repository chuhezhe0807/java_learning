package skills.generic.genericmatrix;

/**
 * ClassName: RationalMatrix
 * Package: generic.genericmatrix
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/10 11:56
 * @Version 1.0
 */
public class RationalMatrix extends GenericMatrix<Rational>{
    @Override
    protected Rational add(Rational o1, Rational o2) {
        return o1.add(o2);
    }

    @Override
    protected Rational multiply(Rational o1, Rational o2) {
        return o1.multiply(o2);
    }

    @Override
    protected Rational zero() {
        return new Rational(0, 1);
    }
}
