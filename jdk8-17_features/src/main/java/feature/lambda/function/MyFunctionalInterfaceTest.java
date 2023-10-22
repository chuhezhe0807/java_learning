package feature.lambda.function;

import org.junit.jupiter.api.Test;

/**
 * ClassName: MyFunctionalInterfaceTest
 * Description:
 *
 * @Author 尚硅谷-宋红康
 * @Create 16:38
 * @Version 1.0
 */
public class MyFunctionalInterfaceTest {
    @Test
    public void test1(){
        MyFunctionalInterface m = () -> System.out.println("hello");

        m.method();
    }
}
