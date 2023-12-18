package designed.pattern.creational.abstractfactory;

/**
 * ClassName: CarFactory
 * Package: designed.pattern.creational.abstractfactory
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 15:25
 * @Version 1.0
 */
public interface CarFactory {
    // 获取车架对象
    CarFrame getCarFrame();

    // 获取地盘对象
    CarBasePlate getCarBasePlate();

    // 获取车门对象
    CarDoor getCarDoor();

    // 生产车
    void make();
}
