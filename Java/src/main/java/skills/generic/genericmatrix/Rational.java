package skills.generic.genericmatrix;

/**
 * 继承Number类提供将表示的数值转换为 byte、double、float、int、long 和 short 的方法。
 *
 * ClassName: Rational
 * Package: generic.genericmatrix
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/10 11:59
 * @Version 1.0
 */
public class Rational extends Number implements Comparable<Rational> {
    private long numerator = 0; // 分子
    private long denominator = 1; // 分母

    public Rational() {
        this(0, 1);
    }

    public Rational(long numerator, long denominator) {
        long gcd = gcd(numerator, denominator);
        this.numerator = ((denominator > 0) ? 1 : -1) * numerator / gcd;
        this.denominator = Math.abs(denominator) / gcd;
    }

    private static long gcd(long n, long d) {
        long n1 = Math.abs(n);
        long n2 = Math.abs(d);
        long remainder = n1 % n2;

        while (remainder != 0) {
            n1 = n2;
            n2 = remainder;
            remainder = n1 % n2;
        }

        return n2;
    }

    /**
     * 最大公约数
     */
    public long getNumerator() {
        return numerator;
    }

    public void setNumerator(long numerator) {
        this.numerator = numerator;
    }

    public long getDenominator() {
        return denominator;
    }

    public void setDenominator(long denominator) {
        this.denominator = denominator;
    }

    public Rational add(Rational augend) {
        long n = numerator * augend.denominator + denominator * augend.numerator;
        long d = denominator * augend.denominator;

        return new Rational(n, d);
    }

    /**
     * 加法
     */
    public Rational subtract(Rational subtrahend) {
        long n = numerator * subtrahend.denominator - denominator * subtrahend.numerator;
        long d = denominator * subtrahend.denominator;

        return new Rational(n, d);
    }

    /**
     * 减法
     */
    public Rational multiply(Rational multiplicand) {
        long n = numerator * multiplicand.numerator;
        long d = denominator * multiplicand.denominator;

        return new Rational(n, d);
    }

    /**
     * 乘法
     */
    public Rational divide(Rational divisor) {
        long n = numerator * divisor.denominator;
        long d = denominator * divisor.numerator;

        return new Rational(n, d);
    }

    /**
     * 除法
     */
    public Rational abs() {
        return (this.numerator < 0 ? negate() : this);
    }

    private Rational negate() {
        long n = ((this.numerator > 0) ? -1 : 1) * this.numerator;

        return new Rational(n, this.denominator);
    }

    /**
     * 获得绝对值
     */
    public int signum() {
        if (numerator > 0) {
            return 1;
        } else if (numerator < 0) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 此方法返回-1，0，或1，此Rational的值分类为负，零或正值。
     */
    @Override
    public int compareTo(Rational val) {
        try {
            if (this.subtract(val).numerator > 0) {
                return 1;
            } else if (this.subtract(val).numerator < 0) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 比较大小
     */
    public boolean equals(Object x) {
        try {
            if (this.subtract((Rational) x).numerator == 0) {
                return true;
            } else {
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    /**
     * 将Rational转为int
     */
    @Override
    public long longValue() {
        return (long) doubleValue();
    }

    /**
     * 将Rational转为long
     */
    @Override
    public float floatValue() {
        return (float) doubleValue();
    }

    /**
     * 将Rational转为float
     */

    @Override
    public double doubleValue() {
        return numerator * 1.0 / denominator;
    }

    @Override
    public String toString() {
        if (denominator == 1) {
            return numerator + "";
        }
        else {
            return numerator + "/" + denominator;
        }
    }
}


