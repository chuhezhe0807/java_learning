package designed.pattern.creational.abstractfactory;

/**
 * ClassName: BasePlate
 * Package: designed.pattern.creational.abstractfactory
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 15:27
 * @Version 1.0
 */
public class CarBasePlate extends CarBasePlateFactory {
    @Override
    public void make() {
        System.out.println("制作底盘");
    }
}
