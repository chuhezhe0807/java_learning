package functionalinterface.predicate;

import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

/**
 * ClassName: PredicateTest
 * Package: functionalinterface.predicate
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/28 22:56
 * @Version 1.0
 */
public class PredicateTest {
    @Test
    public void test01() {
        Predicate<String> predicate01 = (s) -> s.startsWith("A");
        Predicate<String> predicate02 = (s) -> s.endsWith("B");

        System.out.println(predicate01.test("Boo")); // false
        System.out.println(predicate01.test("Aoo")); // true
        System.out.println(predicate01.and(predicate02).test("A00B")); // true
        System.out.println(predicate01.or(predicate02).test("000B")); // true
    }
}
