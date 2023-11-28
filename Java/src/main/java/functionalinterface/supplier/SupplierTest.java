package functionalinterface.supplier;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

/**
 * ClassName: SupplierTest
 * Package: functionalinterface.supplier
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/28 22:53
 * @Version 1.0
 */
public class SupplierTest {
    @Test
    public void test01() {
        Supplier<Integer> supplier01 = () -> 100;

        System.out.println(supplier01.get()); // 100
    }
}
