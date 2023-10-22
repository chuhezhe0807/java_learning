package annotation;

/**
 * ClassName: InitDemo
 * Package: com.chuhezhe.annotation
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/9/3 17:50
 * @Version 1.0
 */
public class InitDemo {
    @InitMethod
    public void init() {
        System.out.println("init....");
    }

    public void test() {
        System.out.println("test....");
    }
}
