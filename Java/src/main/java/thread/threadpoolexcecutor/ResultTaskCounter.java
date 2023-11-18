package thread.threadpoolexcecutor;

import java.util.concurrent.Callable;

/**
 * ClassName: ResultTaskCounter
 * Package: thread.threadpoolexcecutor
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/14 22:06
 * @Version 1.0
 */
public class ResultTaskCounter implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int i = 0;

        // 当线程没有被中断时
        while(!Thread.interrupted()) {
            i++;
        }

        System.out.println(i);

        return 1 + 1;
    }
}
