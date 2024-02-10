package skills.functionalinterface.function;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

/**
 * ClassName: FunctionTest
 * Package: functionalinterface.function
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/28 22:07
 * @Version 1.0
 */
public class FunctionTest {
    @Test
    public void test01() {
        Function<String, String> function01 = (s) -> s + "01";
        Function<String, String> function02 = (s) -> s + "02";
        Function<String, String> function03 = (s) -> s + "03";
        Function<String, String> function04 = (s) -> s + "04";

        // R apply(T t);
        // compose(Function<> before)
        // andThen(Function<> after)
        System.out.println(function01.compose(function02).apply("AAAA")); // AAAA0201
        System.out.println(function01.andThen(function02).apply("BBBB")); // BBBB0102
    }
}
