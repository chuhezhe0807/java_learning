package designed.pattern.creational.abstractfactory;

/**
 * ClassName: CarDoor
 * Package: designed.pattern.creational.abstractfactory
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 15:26
 * @Version 1.0
 */
public class CarDoor extends CarDoorFactory{
    @Override
    public void make() {
        System.out.println("制作车门");
    }
}
