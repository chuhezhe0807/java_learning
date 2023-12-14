package designed.principle.singleresponsibility;

/**
 * ClassName: JavaProgrammer
 * Package: designed.principle.singleresponsibility
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/13 21:46
 * @Version 1.0
 */
public class JavaProgrammer extends Programmer{
    private final Order order;

    public JavaProgrammer(Order order) {
        this.order = order;
    }

    @Override
    public void eat() {
        order.local();
    }

    @Override
    public void sleep() {

    }

    @Override
    public void work() {

    }
}
