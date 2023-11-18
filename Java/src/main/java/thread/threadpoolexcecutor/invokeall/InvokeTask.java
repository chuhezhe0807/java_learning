package thread.threadpoolexcecutor.invokeall;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: InvokeTask
 * Package: thread.threadpoolexcecutor.invokeall
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/17 21:24
 * @Version 1.0
 */
public class InvokeTask implements Callable<Integer> {
    private final Integer index;

    public InvokeTask(Integer index) {
        this.index = index;
    }

    @Override
    public Integer call() throws Exception {
        // 使当前线程休眠1秒钟
        TimeUnit.SECONDS.sleep(1);

        return index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvokeTask that = (InvokeTask) o;
        return Objects.equals(index, that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index);
    }
}
