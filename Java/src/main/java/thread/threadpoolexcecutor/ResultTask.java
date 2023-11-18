package thread.threadpoolexcecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: ResultTask
 * Package: thread.threadpoolexcecutor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/14 21:33
 * @Version 1.0
 */
public class ResultTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(3);

        return 1 + 1;
    }
}
