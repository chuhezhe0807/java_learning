package designed.pattern.creational.abstractfactory;

/**
 * 造车的每一部分都需要抽象工厂管理，最终set到Car上
 *
 * ClassName: Test
 * Package: designed.pattern.creational.abstractfactory
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 15:51
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        Car car = new Car();
        car.getCarDoor();
        car.getCarFrame();
        car.getCarBasePlate();

        car.make();
    }
}
