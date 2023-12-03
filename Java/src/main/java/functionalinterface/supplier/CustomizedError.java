package functionalinterface.supplier;

import java.util.function.Supplier;

/**
 * ClassName: CustomizedError
 * Package: throwable
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/3 14:34
 * @Version 1.0
 */
public class CustomizedError {
    public static <T, X extends Throwable> T notNull(T object, Supplier<X> errorSupplier) throws X {
        if(null == object) {
            throw errorSupplier.get();
        }

        return object;
    }

    public static void main(String[] args) {
//        notNull(new Object(), new IllegalArgumentException("Illegal arguments.")); // 惰性计算 意味着生成值的计算只会在需要时才执行
        notNull(new Object(), () -> new IllegalArgumentException("Illegal arguments."));
    }
}
