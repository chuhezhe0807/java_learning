package functionalinterface.consume;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

/**
 * ClassName: ConsumeTets
 * Package: functionalinterface.consume
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/28 22:36
 * @Version 1.0
 */
public class ConsumeTest {
    @Test
    public void test01() {
        // void accept(T t)
        Consumer<Integer> consumer01 = (i) -> System.out.println(i + 100);
        Consumer<Integer> consumer02 = System.out::println;

        consumer01.accept(1); // 101
        System.out.println("");
        // 由于 accept 方法没有返回值 所以 accept(1) 先执行一遍 consumer1 再执行一遍 consumer2
        // 第一次输出 101 第二次输出1
        consumer01.andThen(consumer02).accept(1);
    }
}
