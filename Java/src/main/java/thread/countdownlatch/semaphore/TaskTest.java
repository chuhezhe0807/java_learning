package thread.countdownlatch.semaphore;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;

/**
 * Semaphore 控制资源的访问数量，更像是一把同步锁
 *
 * ClassName: TaskTest
 * Package: thread.countdownlatch.semaphore
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/11/19 23:30
 * @Version 1.0
 */
public class TaskTest {
    @Test
    public void test01() {
        // 信号量
        Semaphore semaphore = new Semaphore(3);

        for(int i = 0; i < 5; i++) {
            new Thread(new Task(semaphore), "客户 " + i + " 号").start();
        }
    }
}
