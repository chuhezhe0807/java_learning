package designed.pattern.creational.abstractfactory;

/**
 * ClassName: Car
 * Package: designed.pattern.creational.abstractfactory
 * Description:
 *
 * @Author Chuhezhe
 * @Create 2023/12/17 15:32
 * @Version 1.0
 */
public class Car implements CarFactory{
   private CarFrame carFrame = null;
   private CarDoor carDoor = null;
   private CarBasePlate carBasePlate = null;

    @Override
    public CarFrame getCarFrame() {
        carFrame = new CarFrame();

        return carFrame;
    }

    @Override
    public CarBasePlate getCarBasePlate() {
        carBasePlate = new CarBasePlate();

        return carBasePlate;
    }

    @Override
    public CarDoor getCarDoor() {
        carDoor = new CarDoor();

        return carDoor;
    }

    @Override
    public void make() {
        carDoor.make();
        carBasePlate.make();
        carFrame.make();

        System.out.println("车辆生产完成");
    }
}
