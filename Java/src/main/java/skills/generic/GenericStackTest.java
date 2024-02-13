package skills.generic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * ClassName: GenerticTest
 * Package: generic
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2024/2/9 16:10
 * @Version 1.0
 */
public class GenericStackTest {
    @Test
    public void test01() {
        // 子类通过父类的静态检查，父类不能通过子类的静态检查
        ArrayList<Number> list = new ArrayList<>();
        list.add((double) 1);
    }
}
