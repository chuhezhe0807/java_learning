package thread.threadpoolexcecutor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ThreadPoolExecutorTest
 * Package: thread.threadpoolexcecutor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/5 15:42
 * @Version 1.0
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                1,
                2,
                500,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(),
                r -> {
                    Thread t = new Thread(r);
                    t.setName("测试一");

                    return t;
                },
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略
        );

        executor.execute(() -> System.out.println("running...."));

//        executor.submit(); // 与 executor.execute 的区别是，可以传入 Callable 接口实现类，有返回值
    }
}
