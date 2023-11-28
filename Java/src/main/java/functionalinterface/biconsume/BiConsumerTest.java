package functionalinterface.biconsume;

import org.junit.jupiter.api.Test;

import java.util.function.BiConsumer;

/**
 * ClassName: BiConsumerTest
 * Package: functionalinterface.biconsume
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/28 22:49
 * @Version 1.0
 */
public class BiConsumerTest {
    @Test
    public void test01() {
        BiConsumer<Integer, Boolean> biConsumer = (i, b) -> System.out.println(i < 5 || b ? "true" : "false");

        biConsumer.accept(4, Boolean.FALSE);
        biConsumer.accept(5, Boolean.FALSE);
        biConsumer.accept(5, Boolean.TRUE);
    }
}
