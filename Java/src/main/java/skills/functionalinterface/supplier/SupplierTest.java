package skills.functionalinterface.supplier;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

/**
 * 在使用Supplier接口时，有一些注意事项需要考虑：
 * 1. 延迟计算: Supplier通常用于延迟计算或获取值，它并不保证值的立即计算。因此，在调用get方法之前，不会执行Supplier内部的逻辑。
 *      这对于性能优化和避免不必要的计算是有益的. {@link CustomizedError}
 * 2. 可能的空值: Supplier的get方法可以返回null，因此在使用时需要注意处理可能的空值情况，以避免NullPointerException。
 * 3. 线程安全性: 如果多个线程同时访问同一个Supplier实例，并且该实例的get方法可能会修改共享状态，那么你需要确保线程安全性。
 *      你可以考虑使用synchronized关键字或其他线程同步机制来保护共享状态。
 * 4. 性能考虑: 如果Supplier的计算或获取操作涉及昂贵的计算或IO操作，那么你可能需要考虑性能问题。
 *      在某些情况下，你可以使用缓存或懒加载等技术来优化性能。
 * 5. 避免副作用: Supplier的主要目的是提供值，而不是执行副作用。
 *      虽然可以在Supplier内部执行副作用，但最好避免这样做，以保持代码的可预测性和可维护性。
 * 6. 错误处理: 如果Supplier内部的逻辑可能会引发异常，你需要考虑如何处理这些异常。可以使用try-catch块捕获异常并进行适当的处理。
 * 7. 不可变性: 如果可能的话，尽量将Supplier返回的值设计成不可变的，以避免意外的修改。
 *      如果返回的对象是可变的，那么需要特别小心，以确保不会导致意外的状态更改。
 * 8. 维护清晰的代码: 当使用多个Supplier组合时，要确保代码易于阅读和理解。可以使用注释或合理的命名来提高代码的可读性。
 *
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
